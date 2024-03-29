package org.jesperancinha.vma.domain

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Table
data class Song(
    @field: Id
    val idG: String = UUID.randomUUID().toString(),
    val name: String,
    val type: String,
    val year: Int? = null,
    val month: Int? = null,
    val day: Int? = null,
    val created: Long? = null,
    val updates: Int = -1
) : Persistable<String> {
    override fun getId(): String = idG
    override fun isNew(): Boolean = updates < 0
}

@Repository
interface SongRepository : CoroutineCrudRepository<Song, String>