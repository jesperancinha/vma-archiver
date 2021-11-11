package org.jesperancinha.vma.common.dto

class ArtistVotingDto(
    val userId: String? = null,
    val idC: String? = null,
    val idA: String? = null
)

class SongVotingDto(
    val userId: String? = null,
    val idC: String? = null,
    val idS: String? = null
)

class ArtistVotingEvent(
    val userId: String? = null,
    val idC: String? = null,
    val idA: String? = null
)

class SongVotingEvent(
    val userId: String? = null,
    val idC: String? = null,
    val idS: String? = null
)
