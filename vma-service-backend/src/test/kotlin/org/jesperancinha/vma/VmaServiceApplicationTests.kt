package org.jesperancinha.vma

import com.ninjasquad.springmockk.MockkBean
import org.jesperancinha.vma.common.domain.BandRepository
import org.jesperancinha.vma.common.domain.CategoryRepository
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@MockkBean(classes = [CategoryRepository::class, BandRepository::class])
class VmaServiceApplicationTests {

	@Test
	fun contextLoads() {
	}

}
