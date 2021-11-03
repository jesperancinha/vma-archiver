package org.jesperancinha.vma.vmaservice.service

import kotlinx.coroutines.flow.Flow
import org.jesperancinha.vma.vmaservice.domain.Band
import org.jesperancinha.vma.vmaservice.domain.BandRepository
import org.springframework.stereotype.Service

@Service
class ArtistService(
    private val bandRepository: BandRepository
) {
   suspend fun fetchAllBands(): Flow<Band> = bandRepository.findAll()
}