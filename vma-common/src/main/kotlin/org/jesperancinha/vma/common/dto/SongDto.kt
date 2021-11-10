package org.jesperancinha.vma.common.dto

import org.jesperancinha.vma.common.domain.Song

data class SongDto(
    val id: String,
    val name: String,
    val type: String,
    val year: Int? = null,
    val month: Int? = null,
    val day: Int? = null,
)

val SongDto.toData: Song
    get() = Song(
        name = this.name,
        type = this.type,
        year = this.year,
        month = this.month,
        day = this.day
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
