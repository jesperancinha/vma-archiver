package org.jesperancinha.vma.vmaservice.service

import com.hazelcast.core.HazelcastInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.mono
import org.jesperancinha.vma.common.domain.ArtistRepository
import org.jesperancinha.vma.common.domain.Band
import org.jesperancinha.vma.common.domain.BandRepository
import org.jesperancinha.vma.common.domain.CategoryArtistRepository
import org.jesperancinha.vma.common.domain.CategoryRepository
import org.jesperancinha.vma.common.domain.CategorySongRepository
import org.jesperancinha.vma.common.domain.SongRepository
import org.jesperancinha.vma.common.domain.VmaSongDto
import org.jesperancinha.vma.common.domain.VotingCategoryArtistRepository
import org.jesperancinha.vma.common.domain.VotingCategorySongRepository
import org.jesperancinha.vma.common.domain.VotingStatus
import org.jesperancinha.vma.common.domain.saveByIds
import org.jesperancinha.vma.common.domain.toData
import org.jesperancinha.vma.common.dto.ArtistDto
import org.jesperancinha.vma.common.dto.ArtistVotingDto
import org.jesperancinha.vma.common.dto.CategoryDto
import org.jesperancinha.vma.common.dto.CategoryType.ARTIST
import org.jesperancinha.vma.common.dto.CategoryType.INSTRUMENTAL
import org.jesperancinha.vma.common.dto.CategoryType.SONG
import org.jesperancinha.vma.common.dto.SongDto
import org.jesperancinha.vma.common.dto.SongVotingDto
import org.jesperancinha.vma.common.dto.toData
import org.jesperancinha.vma.common.dto.toDto
import org.jesperancinha.vma.common.dto.toDtoWithArtists
import org.jesperancinha.vma.common.dto.toDtoWithArtistsAndVote
import org.jesperancinha.vma.common.dto.toDtoWithSongs
import org.jesperancinha.vma.common.dto.toDtoWithSongsAndVote
import org.jesperancinha.vma.common.dto.toNewData
import org.jesperancinha.vma.vmaservice.kafka.VotingRequestPublisher
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BandService(
    private val bandRepository: BandRepository
) {
    fun fetchAllBands(): Flow<Band> = bandRepository.findAll()

    suspend fun getBandById(id: String) = bandRepository.findById(id)
}

@Service
class SongService(
    private val songRepository: SongRepository
) {
    suspend fun createSong(vmaSongDto: VmaSongDto): SongDto = songRepository.save(vmaSongDto.toData).toDto
    fun findAll(ids: List<String>): Flow<SongDto> = songRepository.findAllById(ids).map { it.toDto }
    suspend fun deleteAll() = songRepository.deleteAll()
}

@Service
class ArtistService(
    private val artistRepository: ArtistRepository,
) {
    suspend fun createArtist(artistDto: ArtistDto): ArtistDto = artistRepository.save(artistDto.toData).toDto

    fun findAll(ids: List<String>): Flow<ArtistDto> = artistRepository.findAllById(ids).map { it.toDto }
    suspend fun deleteAll() = artistRepository.deleteAll()
}

@Service
class CategoryService(
    private val songService: SongService,
    private val artistService: ArtistService,
    private val categoryRepository: CategoryRepository,
    private val categoryArtistRepository: CategoryArtistRepository,
    private val categorySongRepository: CategorySongRepository,
    hazelcastInstance: HazelcastInstance
) {
    private val cache: MutableMap<String, VotingStatus> = hazelcastInstance.getMap("vma-cache")
    suspend fun createRegistry(registryDtos: Flow<CategoryDto>): Flow<CategoryDto> {
        return categoryRepository.deleteAll()
            .also { artistService.deleteAll() }
            .also { songService.deleteAll() }
            .let {
                categoryRepository.saveAll(registryDtos.map { it.toNewData })
                    .map { it.toDto() }
            }
    }

    suspend fun makeRandomGame(vmaSongs: List<VmaSongDto>): Flow<CategoryDto> {
        val artists = vmaSongs
            .map { it.artists }.flatten()
            .distinct()
            .map { artistService.createArtist(ArtistDto(name = it, type = ARTIST)) }
        val songs = vmaSongs
            .map { songService.createSong(it) }

        return categoryRepository.findAll().map {
            when (it.type) {
                ARTIST -> it.toDtoWithArtists(artists.random5(it.capacity)).also { category ->
                    category.artists.forEach { artistDto ->
                        categoryArtistRepository.saveByIds(it.id, artistDto.id)
                    }
                }
                INSTRUMENTAL -> it.toDtoWithSongs(songs.instrumental().random5(it.capacity)).also { category ->
                    category.songs.forEach { songDto ->
                        categorySongRepository.saveByIds(it.id, songDto.id)
                    }
                }
                else -> it.toDtoWithSongs(songs.sung().random5(it.capacity)).also { category ->
                    category.songs.forEach { songDto ->
                        categorySongRepository.saveByIds(it.id, songDto.id)
                    }
                }
            }
        }
    }

    fun findAll(): Flow<CategoryDto> = findAll(null)

    fun findAll(votingKey: String?): Flow<CategoryDto> {
        return categoryRepository.findAll().map { category ->
            when (category.type) {
                ARTIST -> category.toDtoWithArtistsAndVote(
                    artistService.findAll(
                        categoryArtistRepository.findByCategoryId(category.id).map { e -> e.idA }.filterNotNull()
                            .toList()
                    ).toList(), votingKey?.let { cache[votingKey]?.votedOff?.contains(category.id) } ?: false
                )
                else -> category.toDtoWithSongsAndVote(
                    songService.findAll(
                        categorySongRepository.findByCategoryId(category.id).map { e -> e.idS }.filterNotNull().toList()
                    ).toList(), votingKey?.let { cache[votingKey]?.votedOff?.contains(category.id) } ?: false
                )
            }
        }
    }
}

@Service
class VotingService(
    private val votingRequestPublisher: VotingRequestPublisher,
    private val categoryArtistRepository: CategoryArtistRepository,
    private val categorySongRepository: CategorySongRepository,
    private val votingCategoryArtistRepository: VotingCategoryArtistRepository,
    private val votingCategorySongRepository: VotingCategorySongRepository,
    hazelcastInstance: HazelcastInstance
) {
    private val cache: MutableMap<String, VotingStatus> = hazelcastInstance.getMap("vma-cache")

    suspend fun castArtistVote(voterKey: String, artistVotingDto: ArtistVotingDto): Mono<Void> =
        cache[voterKey]?.votedOff?.let { voted ->
            if (!voted.contains(artistVotingDto.idC)) {
                return votingRequestPublisher.publishArtistVote(
                    key = voterKey,
                    artistVotingDto = artistVotingDto.copy(userId = voterKey)
                ).and(mono { voted.add(artistVotingDto.idC) })
            }
        }.let { Mono.empty() }

    suspend fun castSongVote(voterKey: String, songVotingDto: SongVotingDto): Mono<Void> =
        cache[voterKey]?.votedOff?.let { voted ->
            if (!voted.contains(songVotingDto.idC)) {
                return votingRequestPublisher.publishSongVote(
                    key = voterKey,
                    songVotingDto = songVotingDto.copy(userId = voterKey)
                ).and(mono { voted.add(songVotingDto.idC) })
            }
        }.let { Mono.empty() }


    suspend fun countVotes() {
        categoryArtistRepository.findAll().collect { artistCategory ->
            val countByCategoryId = votingCategoryArtistRepository.findCountByCategoryId(artistCategory.idA)
            if (artistCategory.voteCount == 0L) {
                categoryArtistRepository.save(
                    artistCategory.copy(
                        voteCount = countByCategoryId?.toLong() ?: 0,
                        updates = artistCategory.updates + 1
                    )
                )
            }

        }
        categorySongRepository.findAll().collect { songCategory ->
            val countByCategoryId = votingCategorySongRepository.findCountByCategoryId(songCategory.idS)
            if (songCategory.voteCount == 0L) {
                categorySongRepository.save(
                    songCategory.copy(
                        voteCount = countByCategoryId?.toLong() ?: 0,
                        updates = songCategory.updates + 1
                    )
                )
            }
        }
    }

    suspend fun addVotingKeyToCache(votingId: String) {
        if (!cache.containsKey(votingId)) {
            cache[votingId] = VotingStatus(votingId)
        }
    }

    suspend fun getArtistVotingResults(idc: String, ida: String): Long {
        val cat = categoryArtistRepository.findByCategoryIdAndArtistId(idc, ida)
        return cat.votes + cat.voteCount
    }

    suspend fun getSongVotingResults(idc: String, ids: String): Long {
        val cat = categorySongRepository.findByCategoryIdAndSongId(idc, ids)
        return cat.votes + cat.voteCount
    }
}

fun <T> List<T>.random5(capacity: Int): List<T> =
    this.sortedBy { kotlin.random.Random.nextInt(10) - 5 }.subList(0, capacity)

fun List<SongDto>.instrumental(): List<SongDto> = this.filter { it.type.contains(INSTRUMENTAL.toString()) }

fun List<SongDto>.sung(): List<SongDto> = this.filter { it.type.contains(SONG.toString()) }
