package org.jesperancinha.vma.vmaservice.service

import kotlinx.coroutines.flow.Flow
import org.jesperancinha.vma.vmaservice.domain.Band
import org.jesperancinha.vma.vmaservice.domain.BandRepository
import org.jesperancinha.vma.vmaservice.domain.CategoryRepository
import org.springframework.stereotype.Service

@Service
class ArtistService(
    private val bandRepository: BandRepository
) {
    fun fetchAllBands(): Flow<Band> = bandRepository.findAll()

    suspend fun getBandById(id:String) = bandRepository.findById(id)
}

@Service
class CandidatesService(
    private val categoryRepository: CategoryRepository
)