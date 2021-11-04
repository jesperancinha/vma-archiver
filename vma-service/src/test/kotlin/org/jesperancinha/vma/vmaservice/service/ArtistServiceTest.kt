package org.jesperancinha.vma.vmaservice.service

import com.ninjasquad.springmockk.MockkBean
import io.kotest.common.runBlocking
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.longs.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.withContext
import org.jesperancinha.vma.vmaservice.domain.Band
import org.jesperancinha.vma.vmaservice.domain.BandRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*
import kotlin.system.measureTimeMillis


@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [ArtistService::class])
internal class ArtistServiceTest(
    @Autowired
    private val artistService: ArtistService
) {
    @MockkBean
    lateinit var bandRepository: BandRepository

    @Test
    fun `should get all bands when summoning findAll`() = runBlocking {
        val testBand = Band(name = "The Doors")
        every { bandRepository.findAll() } returns flow { emit(testBand) }

        val fetchAllBands = artistService.fetchAllBands()

        fetchAllBands.toList() shouldContain testBand
    }

    @Test
    fun `should get band by Id`() = runBlocking {
        val id = "the-doors" + UUID.randomUUID().toString()
        val testBand = Band(name = "The Doors")
        coEvery { bandRepository.findById(id) } returns testBand

        val band = artistService.getBandById(id)

        band shouldBe testBand
    }

    @Test
    fun `should take exactly 100ms to get two bands with 100ms delay`(): Unit = runBlocking {
        val id = "the-doors" + UUID.randomUUID().toString()
        val testBand = Band(name = "The Doors")
        coEvery { bandRepository.findById(id) } returns testBand

       val processingTime =  measureTimeMillis {
            val dispatcher = newFixedThreadPoolContext(2, "banPoll")
            withContext(dispatcher) {
                val band = artistService.getBandById(id)
                band shouldBe testBand
                delay(100)
            }
            withContext(dispatcher) {
                val band = artistService.getBandById(id)
                band shouldBe testBand
                delay(100)
            }
        }

        processingTime.shouldBeLessThanOrEqual(100)
    }
}