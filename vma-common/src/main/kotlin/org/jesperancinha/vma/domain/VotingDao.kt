package org.jesperancinha.vma.domain

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import java.io.Serializable
import java.util.UUID
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Table
data class VoteCategoryArtist(
    @field: Id
    @field: GeneratedValue(strategy = GenerationType.IDENTITY)
    val idVCA: String = UUID.randomUUID().toString(),
    val userId: String,
    val idC: String,
    val idA: String,
    val created: Long? = null,
    val updates: Int = -1
) : Persistable<String> {
    override fun isNew(): Boolean = updates < 0
    override fun getId(): String = idVCA
}

interface VotingCategoryArtistRepository : CoroutineCrudRepository<VoteCategoryArtist, String> {
    @Query("Select count(id_vca) from vote_category_artist where id_a=:id group by id_a")
    suspend fun findCountByCategoryId(@Param("id") id: String?): Number?
}

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

interface VotingCategorySongRepository : CoroutineCrudRepository<VoteCategorySong, String> {
    @Query("Select count(id_vcs) from vote_category_song where id_s=:id group by id_s")
    suspend fun findCountByCategoryId(@Param("id") id: String?): Number?
}

data class VotingStatus(
    val id: String,
    val allowedArtist: Boolean = true,
    val allowedSong: Boolean = true,
    val votedOff: MutableList<String> = mutableListOf()
) : Serializable