package org.jesperancinha.vma.common.dto

import org.jesperancinha.vma.common.domain.Category


enum class CategoryType {
    INSTRUMENTAL,
    ARTIST,
    SONG
}

data class CategoryDto(
    val id: String? = null,
    val category: String,
    val type: CategoryType?,
    val capacity: Int
)

fun CategoryDto.toData(): Category {
    return Category(
        name = this.category,
        capacity = this.capacity,
        type = this.type
    )
}

fun CategoryDto.toNewData(): Category {
    return Category(
        name = this.category,
        capacity = this.capacity,
        updates = 0,
        type = this.type
    )
}

fun Category.toDto(): CategoryDto {
    return CategoryDto(
        id = this.id,
        category = this.name,
        capacity = this.capacity,
        type = this.type
    )
}
