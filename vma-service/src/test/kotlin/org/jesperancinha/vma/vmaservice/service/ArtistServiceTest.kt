package org.jesperancinha.vma.vmaservice.service

import com.ninjasquad.springmockk.MockkBean
import io.kotest.common.runBlocking
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.longs.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jesperancinha.vma.vmaservice.domain.Band
import org.jesperancinha.vma.vmaservice.domain.BandRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.UUID
import java.util.concurrent.Executors.newFixedThreadPool
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

        val processingTime = measureTimeMillis {
            val dispatcher = newFixedThreadPool(2)
                .asCoroutineDispatcher()
            withContext(dispatcher) {
                delay(100)
                val band = artistService.getBandById(id)
                band shouldBe testBand
            }
            withContext(dispatcher) {
                delay(100)
                val band = artistService.getBandById(id)
                band shouldBe testBand
            }
        }

        processingTime.shouldBeLessThanOrEqual(250)
        coVerify(exactly = 2) { bandRepository.findById(id) }
    }

    /**
     * withContext suspends until completion
     */
    @Test
    fun `should read two using withContext if one takes 100ms and the other 200ms and no waiting on the main thread`(): Unit =
        runBlocking {
            val id = "the-doors" + UUID.randomUUID().toString()
            val testBand = Band(name = "The Doors")
            coEvery { bandRepository.findById(id) } returns testBand

            val dispatcher = newFixedThreadPool(2)
                .asCoroutineDispatcher()
            withContext(dispatcher) {
                delay(100)
                val band = artistService.getBandById(id)
                band shouldBe testBand
            }
            withContext(dispatcher) {
                delay(2000)
                val band = artistService.getBandById(id)
                band shouldBe testBand
            }

            coVerify(exactly = 2) { bandRepository.findById(id) }
        }

    /**
     * coroutineScope waits until completion
     */
    @Test
    fun `should read two using coroutineScope if one takes 100ms and the other 200ms and no waiting on the main thread`() =
        runBlocking {
            val id = "the-doors" + UUID.randomUUID().toString()
            val testBand = Band(name = "The Doors")
            coEvery { bandRepository.findById(id) } returns testBand

            coroutineScope {
                val band = artistService.getBandById(id)
                band shouldBe testBand
                delay(100)
            }
            coroutineScope {
                val band = artistService.getBandById(id)
                band shouldBe testBand
                delay(200)
            }

            coVerify(exactly = 2) { bandRepository.findById(id) }
        }

    /**
     * coroutineScope + async may not wait until completion
     */
    @Test
    fun `should read one using async if one takes 100ms and the other 200ms and no waiting on the main thread`(): Unit =
        runBlocking {
            val id = "the-doors" + UUID.randomUUID().toString()
            val testBand = Band(name = "The Doors")
            coEvery { bandRepository.findById(id) } returns testBand


            val coroutineResult = coroutineScope {
                listOf(
                    async(Dispatchers.IO) {
                        delay(100)
                        val band = artistService.getBandById(id)
                        band shouldBe testBand
                        band
                    }, async(Dispatchers.IO) {
                        suspend {
                            delay(200)
                            val band = artistService.getBandById(id)
                            band shouldBe testBand
                            band
                        }
                    })
            }

            delay(200)
            coVerify(exactly = 1) { bandRepository.findById(id) }

            val result = coroutineResult.awaitAll()
            result.shouldHaveSize(2)
        }


    /**
     * launch
     */
    @Test
    suspend fun `should run launch asynchronously within the same coroutineScope`() {
        runBlocking {
            val id = "the-doors" + UUID.randomUUID().toString()
            val testBand = Band(name = "The Doors")
            coEvery { bandRepository.findById(id) } returns testBand

            coroutineScope {
                launch {
                    val band = artistService.getBandById(id)
                    band shouldBe testBand
                    delay(100)
                }
                launch {
                    val band = artistService.getBandById(id)
                    band shouldBe testBand
                    delay(200)
                }
            }

            coVerify(exactly = 0) { bandRepository.findById(id) }
            delay(100)
            coVerify(exactly = 1) { bandRepository.findById(id) }
            delay(100)
            coVerify(exactly = 2) { bandRepository.findById(id) }

        }

    }
}