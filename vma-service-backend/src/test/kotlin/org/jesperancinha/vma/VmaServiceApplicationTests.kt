package org.jesperancinha.vma

import com.ninjasquad.springmockk.MockkBean
import org.jesperancinha.vma.utils.AbstractVmaTest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("dev")
class VmaServiceApplicationTests : AbstractVmaTest() {

    @Test
    fun contextLoads() {
    }

}
