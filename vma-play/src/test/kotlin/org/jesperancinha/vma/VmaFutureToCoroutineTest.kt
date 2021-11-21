package org.jesperancinha.vma

import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import kotlinx.coroutines.delay
import kotlinx.coroutines.future.await
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.concurrent.CompletableFuture
import kotlin.random.Random

class VmaFutureToCoroutineTest {

    @Test
    fun `should return a random Int using future converted to coroutine call`(): Unit = runBlocking{
        getAnIntNumber() shouldBeGreaterThanOrEqual Int.MIN_VALUE
        getAnIntNumber() shouldBeLessThanOrEqual Int.MAX_VALUE
    }

    private suspend fun getAnIntNumber(): Int {
        delay(1000)
        val cf = CompletableFuture.supplyAsync { Random.nextInt() }
        return cf.await()
    }
}