package org.jesperancinha.vma.common.dto

import org.jesperancinha.vma.common.domain.Category

data class CategoryDto(
    val id: String? = null,
    val category: String,
    val capacity: Int
)

fun CategoryDto.toData(): Category {
    return Category(
        name = this.category,
        capacity = this.capacity
    )
}

fun Category.toDto(): CategoryDto {
    return CategoryDto(
        id = this.id,
        category = this.name,
        capacity = this.capacity
    )
}
