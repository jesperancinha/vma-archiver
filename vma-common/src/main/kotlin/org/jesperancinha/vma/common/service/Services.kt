package org.jesperancinha.vma.common.service

import com.hazelcast.core.HazelcastInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.jesperancinha.vma.common.domain.*
import org.jesperancinha.vma.common.dto.*
import org.jesperancinha.vma.common.dto.CategoryType.*
import org.springframework.stereotype.Service

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

    fun findAll(votingKey: String?): Flow<CategoryDto> = categoryRepository.findAll().map { category ->
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

fun <T> List<T>.random5(capacity: Int): List<T> =
    this.sortedBy { kotlin.random.Random.nextInt(10) - 5 }.subList(0, capacity)

fun List<SongDto>.instrumental(): List<SongDto> = this.filter { it.type.contains(INSTRUMENTAL.toString()) }

fun List<SongDto>.sung(): List<SongDto> = this.filter { it.type.contains(SONG.toString()) }
