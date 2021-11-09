package org.jesperancinha.vma.vmaservice.rest

import kotlinx.coroutines.flow.Flow
import org.jesperancinha.vma.common.dto.CategoryDto
import org.jesperancinha.vma.vmaservice.service.CategoryService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class RegistryController(
    val categoryService: CategoryService
) {
    @PostMapping
    suspend fun createCategories(
        @RequestBody
        registryDtos: Flow<CategoryDto>
    ): Flow<CategoryDto> {
        return categoryService.createRegistry(registryDtos)
    }

}