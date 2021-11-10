package org.jesperancinha.vma.vmaservice.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.jesperancinha.vma.common.domain.ArtistRepository
import org.jesperancinha.vma.common.domain.Band
import org.jesperancinha.vma.common.domain.BandRepository
import org.jesperancinha.vma.common.domain.CategoryRepository
import org.jesperancinha.vma.common.domain.SongRepository
import org.jesperancinha.vma.common.domain.VmaSongDto
import org.jesperancinha.vma.common.domain.toData
import org.jesperancinha.vma.common.dto.ArtistDto
import org.jesperancinha.vma.common.dto.CategoryDto
import org.jesperancinha.vma.common.dto.CategoryType.ARTIST
import org.jesperancinha.vma.common.dto.CategoryType.INSTRUMENTAL
import org.jesperancinha.vma.common.dto.CategoryType.SONG
import org.jesperancinha.vma.common.dto.SongDto
import org.jesperancinha.vma.common.dto.toData
import org.jesperancinha.vma.common.dto.toDto
import org.jesperancinha.vma.common.dto.toNewData
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
}

@Service
class ArtistService(
    private val artistRepository: ArtistRepository,
) {
    suspend fun createArtist(artistDto: ArtistDto): ArtistDto = artistRepository.save(artistDto.toData).toDto
}

@Service
class CategoryService(
    private val songService: SongService,
    private val artistService: ArtistService,
    private val categoryRepository: CategoryRepository,
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
                ARTIST -> artists.random5(it.capacity).forEach { println(it) }
                INSTRUMENTAL -> songs.instrumental().random5(it.capacity).forEach { println(it) }
                SONG -> songs.sung().random5(it.capacity).forEach { println(it) }
                else -> {
                }
            }
            it
        }.map { it.toDto() }
    }
}

fun <T> List<T>.random5(capacity: Int): List<T> =
    this.sortedBy { kotlin.random.Random.nextInt(10) - 5 }.subList(0, capacity)

fun List<SongDto>.instrumental(): List<SongDto> = this.filter { it.type.contains(INSTRUMENTAL.toString()) }

fun List<SongDto>.sung(): List<SongDto> = this.filter { it.type.contains(SONG.toString()) }
