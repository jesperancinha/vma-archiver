package org.jesperancinha.vma.vmaservice.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.jesperancinha.vma.common.domain.Band
import org.jesperancinha.vma.common.domain.BandRepository
import org.jesperancinha.vma.common.domain.CategoryRepository
import org.jesperancinha.vma.common.dto.CategoryDto
import org.jesperancinha.vma.common.dto.toData
import org.jesperancinha.vma.common.dto.toDto
import org.springframework.stereotype.Service

@Service
class ArtistService(
    private val bandRepository: BandRepository
) {
    fun fetchAllBands(): Flow<Band> = bandRepository.findAll()

    suspend fun getBandById(id: String) = bandRepository.findById(id)
}

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    fun createRegistry(registryDtos: Flow<CategoryDto>): Flow<CategoryDto> {
        return categoryRepository.saveAll(registryDtos.map { categoryRepository.save(it.toData()) })
            .map { it.toDto() }
    }
}