package org.jesperancinha.vma.common.domain

import kotlinx.coroutines.flow.Flow
import org.jesperancinha.vma.common.dto.CategoryType
import org.springframework.data.annotation.Version
import org.springframework.data.domain.Persistable
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Table
data class Category(
    @field: org.springframework.data.annotation.Id
    val idC: String = UUID.randomUUID().toString(),
    val name: String,
    val capacity: Int,
    val created: Long? = null,
    val updates: Int = -1,
    val type: CategoryType? = null
) : Persistable<String> {
    override fun isNew(): Boolean = updates == 0
    override fun getId(): String = idC
}

@Table
data class CategoryArtist(
    @field: org.springframework.data.annotation.Id
    val idCA: String = UUID.randomUUID().toString(),
    val idC: String? = null,
    val idA: String? = null,
    val updates: Int = -1,
    val votes: Int = 0,
    val voteCount: Int =0,
    @field: Version
    val version: Long? = null,
) : Persistable<String> {
    override fun getId(): String = idCA
    override fun isNew(): Boolean = updates < 0
}

@Table
data class CategorySong(
    @field: org.springframework.data.annotation.Id
    val idCS: String = UUID.randomUUID().toString(),
    val idC: String? = null,
    val idS: String? = null,
    val updates: Int = -1,
    val votes: Int = 0,
    val voteCount: Int =0,
    @field: Version
    val version: Long? = null,
) : Persistable<String> {
    override fun getId(): String = idCS
    override fun isNew(): Boolean = updates < 0
}

@Repository
interface CategoryRepository : CoroutineCrudRepository<Category, String>

@Repository
interface CategoryArtistRepository : CoroutineCrudRepository<CategoryArtist, String> {
    @Query("Select * from category_artist ca where ca.id_c=:idc")
    fun findByCategoryId(@Param("idc") categoryId: String): Flow<CategoryArtist>

    @Query("Select * from category_artist ca where ca.id_c=:idc and ca.id_a=:ida")
    suspend fun findByCategoryIdAndArtistId(
        @Param("idc") categoryId: String,
        @Param("ida") artistId: String
    ): CategoryArtist
}

@Repository
interface CategorySongRepository : CoroutineCrudRepository<CategorySong, String> {
    @Query("Select * from category_song cg where cg.id_c=:idc")
    fun findByCategoryId(@Param("idc") categoryId: String): Flow<CategorySong>

    @Query("Select * from category_song ca where ca.id_c=:idc and ca.id_s=:ids")
    suspend fun findByCategoryIdAndSongId(
        @Param("idc") categoryId: String,
        @Param("ids") songId: String
    ): CategorySong
}


suspend fun CategoryArtistRepository.saveByIds(idC: String, idA: String?) = this.save(
    CategoryArtist(
        idC = idC,
        idA = idA
    )
)

suspend fun CategorySongRepository.saveByIds(idC: String, idS: String?) = this.save(
    CategorySong(
        idC = idC,
        idS = idS
    )
)
