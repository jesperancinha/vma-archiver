package org.jesperancinha.vma.vmaservice.rest

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.jesperancinha.vma.common.domain.VmaSongDto
import org.jesperancinha.vma.dto.CategoryDto
import org.jesperancinha.vma.vmaservice.services.CategoryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("registry")
class RegistryController(
    val categoryService: CategoryService
) {
    @PostMapping
    suspend fun createCategories(
        @RequestBody
        registryDtos: List<CategoryDto>
    ): Flow<CategoryDto> = categoryService.createRegistry(registryDtos.asFlow())

    @PostMapping("/random")
    suspend fun createRandomVma(
        @RequestBody
        vmaSongs: List<VmaSongDto>
    ): Flow<CategoryDto> = categoryService.makeRandomGame(vmaSongs)

    @GetMapping("/current")
    fun getCurrentVma(@CookieValue("votingId") votingKey: String?): Flow<CategoryDto> =
        categoryService.findAll(votingKey)

    @GetMapping
    fun getAllCategories(@CookieValue("votingId") votingKey: String?): Flow<CategoryDto> =
        categoryService.findAll()
}