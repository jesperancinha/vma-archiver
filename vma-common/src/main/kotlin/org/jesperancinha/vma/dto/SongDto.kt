package org.jesperancinha.vma.dto

import org.jesperancinha.vma.domain.Song

data class SongDto(
    val id: String,
    val name: String,
    val type: String,
    val year: Int? = null,
    val month: Int? = null,
    val day: Int? = null,
)

val Song.toDto: SongDto
    get() = SongDto(
        id = this.id,
        name = this.name,
        type = this.type,
        year = this.year,
        month = this.month,
        day = this.day
    )
