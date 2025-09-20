package org.jesperancinha.vma.vmaservice.service

import com.ninjasquad.springmockk.MockkBean
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
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jesperancinha.vma.domain.Band
import org.jesperancinha.vma.domain.BandRepository
import org.jesperancinha.vma.vmaservice.services.BandService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.UUID
import java.util.concurrent.Executors.newFixedThreadPool
import kotlin.system.measureTimeMillis


@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [BandService::class])
internal class BandServiceTest(
    @Autowired
    private val bandService: BandService
) {
    @MockkBean
    lateinit var bandRepository: BandRepository

    @Test
    fun `should get all bands when summoning findAll`(): Unit = runBlocking {
        val testBand = Band(name = "The Doors")
        every { bandRepository.findAll() } returns flow { emit(testBand) }

        val fetchAllBands = bandService.fetchAllBands()

        fetchAllBands.toList() shouldContain testBand
    }

    @Test
    fun `should get band by Id`(): Unit = runBlocking {
        val id = "the-doors" + UUID.randomUUID().toString()
        val testBand = Band(name = "The Doors")
        coEvery { bandRepository.findById(id) } returns testBand

        val band = bandService.getBandById(id)

        band shouldBe testBand
    }

    @Test
    fun `should take exactly 100ms to get two bands with minimum 100ms delay`(): Unit = runBlocking {
        val id = "the-doors" + UUID.randomUUID().toString()
        val testBand = Band(name = "The Doors")
        coEvery { bandRepository.findById(id) } returns testBand

        val processingTime = measureTimeMillis {
            val dispatcher = newFixedThreadPool(2)
                .asCoroutineDispatcher()
            withContext(dispatcher) {
                delay(100)
                val band = bandService.getBandById(id)
                band shouldBe testBand
            }
            withContext(dispatcher) {
                delay(100)
                val band = bandService.getBandById(id)
                band shouldBe testBand
            }
        }

        processingTime.shouldBeLessThanOrEqual(300)
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
                val band = bandService.getBandById(id)
                band shouldBe testBand
            }
            withContext(dispatcher) {
                delay(2000)
                val band = bandService.getBandById(id)
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
                val band = bandService.getBandById(id)
                band shouldBe testBand
                delay(100)
            }
            coroutineScope {
                val band = bandService.getBandById(id)
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
                        val band = bandService.getBandById(id)
                        band shouldBe testBand
                        band
                    }, async(Dispatchers.IO) {
                        suspend {
                            delay(200)
                            val band = bandService.getBandById(id)
                            band shouldBe testBand
                            band
                        }
                    })
            }

            delay(150)
            coVerify(exactly = 1) { bandRepository.findById(id) }

            val result = coroutineResult.awaitAll()
            result.shouldHaveSize(2)
        }


    /**
     * launch coroutines
     */
    @Test
    fun `should run launch asynchronously within the same coroutineScope`(): Unit = runBlocking(Dispatchers.IO) {
        val id = "the-doors" + UUID.randomUUID().toString()
        val testBand = Band(name = "The Doors")
        coEvery { bandRepository.findById(id) } returns testBand

        coroutineScope {
            launch {
                delay(100)
                val band = bandService.getBandById(id)
                band shouldBe testBand
            }
            launch {
                delay(200)
                val band = bandService.getBandById(id)
                band shouldBe testBand

            }
            coVerify(exactly = 0) { bandRepository.findById(id) }
            delay(100)
            coVerify(exactly = 1) { bandRepository.findById(id) }
            delay(100)
            coVerify(exactly = 2) { bandRepository.findById(id) }

        }
    }

    /**
     * await for asynchronous coroutine
     */
    @Test
    fun `should await for the asynchronous coroutine to complete`(): Unit =
        runBlocking {
            val id = "the-doors" + UUID.randomUUID().toString()
            val testBand = Band(name = "The Doors")
            coEvery { bandRepository.findById(id) } returns testBand

            val coroutineResult = coroutineScope {
                async(Dispatchers.IO) {
                    delay(100)
                    val band = bandService.getBandById(id)
                    band shouldBe testBand
                    band
                }
            }

            delay(100)
            coVerify(exactly = 1) { bandRepository.findById(id) }

            val result = coroutineResult.await()
            result shouldBe testBand
        }

}