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
    val capacity: Int,
    val artists: List<ArtistDto> = emptyList(),
    val songs: List<SongDto> = emptyList()
)

fun CategoryDto.toData(): Category = Category(
    name = this.category,
    capacity = this.capacity,
    type = this.type
)

val CategoryDto.toNewData: Category
    get() = Category(
        name = this.category,
        capacity = this.capacity,
        updates = 0,
        type = this.type
    )

fun Category.toDto(): CategoryDto = CategoryDto(
    id = this.id,
    category = this.name,
    capacity = this.capacity,
    type = this.type
)

fun Category.toDtoWithArtists(artists: List<ArtistDto>): CategoryDto = CategoryDto(
    id = this.id,
    category = this.name,
    capacity = this.capacity,
    type = this.type,
    artists = artists
)

fun Category.toDtoWithSongs(songs: List<SongDto>): CategoryDto = CategoryDto(
    id = this.id,
    category = this.name,
    capacity = this.capacity,
    type = this.type,
    songs = songs
)

