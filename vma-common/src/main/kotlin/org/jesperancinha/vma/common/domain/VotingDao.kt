package org.jesperancinha.vma.common.domain.kafka

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.UUID
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Table
data class VoteCategoryArtist(
    @field: Id
    @field: GeneratedValue(strategy = GenerationType.IDENTITY)
    val idVCS: String = UUID.randomUUID().toString(),
    val userId: String,
    val idC: String,
    val idA: String,
    val created: Long? = null,
    val updates: Int = -1
) : Persistable<String> {
    override fun isNew(): Boolean = updates < 0
    override fun getId(): String = idVCS
}

interface VotingCategoryArtistRepository : CoroutineCrudRepository<VoteCategoryArtist, String>

@Table
data class VoteCategorySong(
    @field: Id
    @field: GeneratedValue(strategy = GenerationType.IDENTITY)
    val idVCS: String = UUID.randomUUID().toString(),
    val userId: String,
    val idC: String,
    val idS: String,
    val created: Long? = null,
    val updates: Int = -1
) : Persistable<String> {
    override fun isNew(): Boolean = updates < 0
    override fun getId(): String = idVCS
}

interface VotingCategorySongRepository : CoroutineCrudRepository<VoteCategorySong, String>

