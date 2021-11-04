package org.jesperancinha.vma.vmaservice.service

import com.ninjasquad.springmockk.MockkBean
import io.kotest.common.runBlocking
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import org.jesperancinha.vma.vmaservice.domain.Band
import org.jesperancinha.vma.vmaservice.domain.BandRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*


@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [ArtistService::class])
internal class ArtistServiceTest(
    @Autowired
    private val artistService: ArtistService
) {

    @MockkBean
    lateinit var bandRepository: BandRepository


    @Test
    fun fetchAllBands() = runBlocking {
        val testBand = Band(name = "The Doors")
        every { bandRepository.findAll() } returns flow { emit(testBand) }

        val fetchAllBands = artistService.fetchAllBands()

        fetchAllBands.toList() shouldContain testBand
    }

    @Test
    fun getBandById() = runBlocking {
        val id = "the-doors" + UUID.randomUUID().toString()
        val testBand = Band(name = "The Doors")
        coEvery { bandRepository.findById(id) } returns testBand

        val band = artistService.getBandById(id)

        band shouldBe testBand
    }
}