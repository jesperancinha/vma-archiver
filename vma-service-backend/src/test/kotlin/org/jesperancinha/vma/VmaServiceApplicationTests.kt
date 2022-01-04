package org.jesperancinha.vma

import org.jesperancinha.vma.utils.AbstractVmaTest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("dev")
class VmaServiceApplicationTests : AbstractVmaTest() {

    @Test
    fun contextLoads() {
    }

}
