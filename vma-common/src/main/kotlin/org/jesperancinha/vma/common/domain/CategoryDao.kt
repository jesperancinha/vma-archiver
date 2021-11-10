package org.jesperancinha.vma.common.domain

import kotlinx.coroutines.flow.Flow
import org.jesperancinha.vma.common.dto.CategoryType
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
@Entity
data class Category(
    @field: Id
    @field: org.springframework.data.annotation.Id
    val idC: String = UUID.randomUUID().toString(),

    val name: String,

    val capacity: Int,

    val created: Long? = null,

    val updates: Int = -1,

    val type: CategoryType? = null

//    @field: OneToOne
//    val winner: Artist? = null

) : Persistable<String> {
    override fun isNew(): Boolean {
        return updates == 0
    }

    override fun getId(): String {
        return idC
    }
}

@Entity
@Table
data class CategoryArtist(
    @field: Id
    @field: org.springframework.data.annotation.Id
    val idCA: String = UUID.randomUUID().toString(),
    val idC: String? = null,
    val idA: String? = null,
    val updates: Int = -1
) : Persistable<String> {
    override fun getId(): String = idCA

    override fun isNew(): Boolean = updates < 0
}

@Entity
@Table
data class CategorySong(
    @field: Id
    @field: org.springframework.data.annotation.Id
    val idCS: String = UUID.randomUUID().toString(),
    val idC: String? = null,
    val idS: String? = null,
    val updates: Int = -1
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
}

@Repository
interface CategorySongRepository : CoroutineCrudRepository<CategorySong, String> {

    @Query("Select * from category_song cg where cg.id_c=:idc")
    fun findByCategoryId(@Param("idc") categoryId: String): Flow<CategorySong>
}
