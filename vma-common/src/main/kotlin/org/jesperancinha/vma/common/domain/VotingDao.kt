package org.jesperancinha.vma.common.domain.kafka

import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Table
@Entity
data class VoteCategoryArtist(
    @field: Id @field: GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String = UUID.randomUUID().toString(),
    val userId: String? = null,
    val idC: String? = null,
    val idA: String? = null
)

interface VotingCategoryArtistRepository : CoroutineCrudRepository<VoteCategoryArtist, String>

@Table
@Entity
data class VoteCategorySong(
    @field: Id @field: GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String = UUID.randomUUID().toString(),
    val userId: String? = null,
    val idC: String? = null,
    val idS: String? = null
)

interface VotingCategorySongRepository : CoroutineCrudRepository<VoteCategorySong, String>

