package org.jesperancinha.vma.vmaservice.rest

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.jesperancinha.vma.common.domain.VmaSongDto
import org.jesperancinha.vma.common.dto.CategoryDto
import org.jesperancinha.vma.vmaservice.service.CategoryService
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("registry")
class RegistryController(
    private val template: SimpMessagingTemplate,
    val categoryService: CategoryService
) {
    @PostMapping
    fun createCategories(
        @RequestBody
        registryDtos: List<CategoryDto>
    ): Flow<CategoryDto> = categoryService.createRegistry(registryDtos.asFlow())

    @PostMapping("/random")
    suspend fun createRandomVma(
        @RequestBody
        vmaSongs: List<VmaSongDto>
    ): Flow<CategoryDto> = categoryService.makeRandomGame(vmaSongs)

    @GetMapping("/current")
    suspend fun getCurrentVma(): Flow<CategoryDto> = categoryService.findAll()

    @Scheduled(fixedDelay = 5000)
    fun update() {
        template.convertAndSend("/topic/vma", categoryService.findAll())
    }
}