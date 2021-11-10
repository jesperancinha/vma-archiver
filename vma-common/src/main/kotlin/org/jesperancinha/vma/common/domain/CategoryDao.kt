package org.jesperancinha.vma.common.domain

import org.jesperancinha.vma.common.dto.CategoryType
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
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
    val idC: String = UUID.randomUUID().toString(),
    val idA: String = UUID.randomUUID().toString()
)

@Repository
interface CategoryRepository : CoroutineCrudRepository<Category, String>

@Repository
interface CategoryArtistRepository : CoroutineCrudRepository<CategoryArtist, String>