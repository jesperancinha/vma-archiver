package org.jesperancinha.vma.vmaservice.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.jesperancinha.vma.common.domain.Artist
import org.jesperancinha.vma.common.domain.ArtistRepository
import org.jesperancinha.vma.common.domain.Band
import org.jesperancinha.vma.common.domain.BandRepository
import org.jesperancinha.vma.common.domain.CategoryArtistRepository
import org.jesperancinha.vma.common.domain.CategoryRepository
import org.jesperancinha.vma.common.domain.CategorySongRepository
import org.jesperancinha.vma.common.domain.Song
import org.jesperancinha.vma.common.domain.SongRepository
import org.jesperancinha.vma.common.domain.VmaSongDto
import org.jesperancinha.vma.common.domain.kafka.VoteCategorySong
import org.jesperancinha.vma.common.domain.kafka.VotingCategoryArtistRepository
import org.jesperancinha.vma.common.domain.kafka.VotingCategorySongRepository
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
import org.jesperancinha.vma.common.dto.toDtoWithSongs
import org.jesperancinha.vma.common.dto.toNewData
import org.jesperancinha.vma.vmaservice.kafka.VotingRequestPublisher
import org.springframework.stereotype.Service

@Service
class BandService(
    private val bandRepository: BandRepository
) {
    fun fetchAllBands(): Flow<Band> = bandRepository.findAll()

    suspend fun getBandById(id: String) = bandRepository.findById(id)

    suspend fun createArtist(): Flow<Band> = bandRepository.findAll()
}

@Service
class SongService(
    private val songRepository: SongRepository
) {
    suspend fun createSong(vmaSongDto: VmaSongDto): SongDto = songRepository.save(vmaSongDto.toData).toDto
    fun findAll(ids: List<String>): Flow<Song> = songRepository.findAllById(ids)
}

@Service
class ArtistService(
    private val artistRepository: ArtistRepository,
) {
    suspend fun createArtist(artistDto: ArtistDto): ArtistDto = artistRepository.save(artistDto.toData).toDto

    fun findAll(ids: List<String>): Flow<Artist> = artistRepository.findAllById(ids)
}

@Service
class CategoryService(
    private val songService: SongService,
    private val artistService: ArtistService,
    private val categoryRepository: CategoryRepository,
    private val categoryArtistRepository: CategoryArtistRepository,
    private val categorySongRepository: CategorySongRepository
) {
    fun createRegistry(registryDtos: Flow<CategoryDto>): Flow<CategoryDto> {
        return categoryRepository.saveAll(registryDtos.map { it.toNewData })
            .map { it.toDto() }
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

    fun findAll(): Flow<CategoryDto> {
        return categoryRepository.findAll().map {
            when (it.type) {
                ARTIST -> it.toDtoWithArtists(
                    artistService.findAll(
                        categoryArtistRepository.findByCategoryId(it.id).map { e -> e.idA }.filterNotNull().toList()
                    ).toList().map { artist -> artist.toDto })
                else -> it.toDtoWithSongs(
                    songService.findAll(
                        categorySongRepository.findByCategoryId(it.id).map { e -> e.idS }.filterNotNull().toList()
                    ).toList().map { song -> song.toDto })
            }
        }
    }
}

@Service
class VotingService(
    private val votingRequestPublisher: VotingRequestPublisher
) {
    fun castArtistVote(voterKey: String, artistVotingDto: ArtistVotingDto) = votingRequestPublisher.publishArtistVote(key = voterKey, artistVotingDto = artistVotingDto)

    fun castSongVote(voterKey: String, songVotingDto: SongVotingDto) = votingRequestPublisher.publishSongVote(key = voterKey, songVotingDto = songVotingDto)

}

fun <T> List<T>.random5(capacity: Int): List<T> =
    this.sortedBy { kotlin.random.Random.nextInt(10) - 5 }.subList(0, capacity)

fun List<SongDto>.instrumental(): List<SongDto> = this.filter { it.type.contains(INSTRUMENTAL.toString()) }

fun List<SongDto>.sung(): List<SongDto> = this.filter { it.type.contains(SONG.toString()) }
