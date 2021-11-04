package org.jesperancinha.vma

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class VmaValOverrideTest {

    @Test
    fun `should deliver the right message for each service`() {
        CupService1(CoffeeCupImpl()).cupMessage() shouldBe "Hello I'm a Coffee Cup"
        CupService2(CoffeeCupImpl()).cupMessage() shouldBe "Hello I'm a Coffee Cup"
        CupService3(BeerCupImpl()).cupMessage() shouldBe "Hello I'm a Beer Cup"
        CupService4(BeerCupImpl()).cupMessage() shouldBe "Hello I'm a Beer Cup"
    }

}

abstract class CupService<B, T>(open val cup: Cup<B, T>? = null) {

    abstract fun cupMessage(): String
}

class CupService1(
    cup: CoffeeCupImpl
) : CupService<Long, Long>(cup) {
    override fun cupMessage(): String = (cup as CoffeeCupImpl).getCoffeCupHello()

}

class CupService2(
    override val cup: CoffeeCupImpl
) : CupService<Long, Long>() {
    override fun cupMessage(): String = cup.getCoffeCupHello()

}

class CupService3(
    cup: BeerCupImpl
) : CupService<String, Long>(cup) {
    override fun cupMessage(): String = (cup as BeerCupImpl).getBeerCupHello()

}

class CupService4(
    override val cup: BeerCupImpl
) : CupService<String, Long>() {
    override fun cupMessage(): String = cup.getBeerCupHello()

}

interface Cup<V, T>

interface BeerCup : Cup<String, Long>

interface CoffeeCup : Cup<Long, Long>

class BeerCupImpl : BeerCup {
    fun getBeerCupHello(): String = "Hello I'm a Beer Cup"
}

class CoffeeCupImpl : CoffeeCup {
    fun getCoffeCupHello() = "Hello I'm a Coffee Cup"
}