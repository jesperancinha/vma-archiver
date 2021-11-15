package org.jesperancinha.vma.common.domain

import org.jesperancinha.vma.common.dto.CategoryType


/**
 * Song registration.
 * Top register this VMA's we receive data in its rawest form.
 * The song is associated with the artist, and it is part of a list of CategoryType
 */
data class VmaSongDto(
    val name: String,
    val artists: List<String>,
    val types: List<CategoryType>
)

val VmaSongDto.toData: Song
    get() = Song(
        name = this.name,
        type = this.types.joinToString(",") { it.toString() }
    )

enum class VmaStatus{
    VOTING,
    VOTED,
    RESUTS
}

data class VmaAwards(
    var vmaStatus: VmaStatus = VmaStatus.VOTING
)