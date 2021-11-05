package org.jesperancinha.vma

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class VmaPlayTest {

    @Test
    fun `should print stackstrace`() {
        val message = Thread.currentThread().stackTrace.contentDeepToString().replace(",", "\n")
        print(message)
    }

    @Test
    fun `should show stacktrace for normal recursive`() {
        val wierdFormulaPlain = wierdFormulaPlain(10, 5.0)

        wierdFormulaPlain shouldBe  55.0

    }


    /**
     * Tail rec functions are functions that end in the call to the function itself or an end value
     * The accumulator value is passed through in every recursive call
     * tailrec indicates that to the developer
     */
    @Test
    fun `should show stacktrace for tailrec`() {
        val wierdFormulaTailRec = wierdFormulaTailRec(10, 5.0)

        wierdFormulaTailRec shouldBe 55.0
    }

    private fun wierdFormulaPlain(n: Int, value: Double): Double = when (n) {
        -1 -> 0.0
        else -> value + wierdFormulaPlain(n - 1, value)
    }

    private tailrec fun wierdFormulaTailRec(n: Int, value: Double): Double = when (n) {
        0 -> value
        else -> wierdFormulaTailRec(n - 1, value / n + value)

    }
}
