package org.jesperancinha.vma.common.dto

import org.jesperancinha.vma.common.domain.Category

data class CategoryDto(
    val id: String? = null,
    val name: String,
    val capacity: Int
)

fun CategoryDto.toData(): Category {
    return Category(
        name = this.name,
        capacity = this.capacity
    )
}

fun Category.toDto(): CategoryDto {
    return CategoryDto(
        id = this.id,
        name = this.name,
        capacity = this.capacity
    )
}
