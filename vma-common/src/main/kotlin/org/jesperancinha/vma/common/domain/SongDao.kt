package org.jesperancinha.vma.common.domain

import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@Table
data class Song(
    @field: Id
    @field: org.springframework.data.annotation.Id
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