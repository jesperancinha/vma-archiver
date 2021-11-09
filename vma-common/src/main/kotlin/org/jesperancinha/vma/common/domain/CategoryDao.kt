package org.jesperancinha.vma.common.domain

import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Table
@Entity
data class Category(
    @field: Id @field: GeneratedValue(strategy = GenerationType.IDENTITY)
    @field: org.springframework.data.annotation.Id
    val idC: String = UUID.randomUUID().toString(),

    @field: Column
    val name: String,

    @field: Column
    val capacity: Int,

    @field: Column
    val created: Long? = null,

//    @field: OneToMany
//    val candidates: List<Artist> = emptyList(),

//    @field: OneToOne
//    val winner: Artist? = null

) : Persistable<String> {
    override fun isNew(): Boolean {
        return created == null
    }

    override fun getId(): String {
        return idC
    }
}

@Repository
interface CategoryRepository : CoroutineCrudRepository<Category, String>

