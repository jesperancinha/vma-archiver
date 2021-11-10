package org.jesperancinha.vma.vmaservice.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.jesperancinha.vma.common.domain.Artist
import org.jesperancinha.vma.common.domain.ArtistRepository
import org.jesperancinha.vma.common.domain.Band
import org.jesperancinha.vma.common.domain.BandRepository
import org.jesperancinha.vma.common.domain.CategoryRepository
import org.jesperancinha.vma.common.domain.VmaSongDto
import org.jesperancinha.vma.common.dto.CategoryDto
import org.jesperancinha.vma.common.dto.CategoryType
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
class ArtistService(
    private val artistRepository: ArtistRepository,
) {
    suspend fun createArtist(artist: Artist): Artist = artistRepository.save(artist)
}

@Service
class CategoryService(
    private val artistService: ArtistService,
    private val categoryRepository: CategoryRepository,
) {
    fun createRegistry(registryDtos: Flow<CategoryDto>): Flow<CategoryDto> {
        return categoryRepository.saveAll(registryDtos.map { it.toNewData() })
            .map { it.toDto() }
    }

    suspend fun makeRandomGame(vmaSongs: List<VmaSongDto>): Flow<CategoryDto> {
        val artists = vmaSongs
            .filter { it.types.contains(CategoryType.ARTIST) }
            .map { it.artists }.flatten()
            .map { artistService.createArtist(Artist(name = it)) }
        val songs = vmaSongs
            .filter { it.types.contains(CategoryType.SONG) }
            .map { it.artists }.flatten()
            .map { artistService.createArtist(Artist(name = it)) }
        val instrumental = vmaSongs
            .filter { it.types.contains(CategoryType.INSTRUMENTAL) }
            .map { it.artists }.flatten()
            .map { artistService.createArtist(Artist(name = it)) }

        return categoryRepository.findAll().map { it.toDto() }
    }
}