package org.jesperancinha.vma.dto

import org.jesperancinha.vma.domain.Artist

data class ArtistDto(
    val id: String? = null,
    val name: String,
    val type: CategoryType,
)

val ArtistDto.toData: Artist
    get() = Artist(
        name = this.name,
        updates = -1,
        type = this.type
    )

val Artist.toDto: ArtistDto
    get() = ArtistDto(
        id = this.id,
        name = this.name,
        type = this.type
    )