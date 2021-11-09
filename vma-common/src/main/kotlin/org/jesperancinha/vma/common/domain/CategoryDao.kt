package org.jesperancinha.vma.common.domain

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
    val id: String = UUID.randomUUID().toString(),

    @field: Column
    val name: String,

    @field: Column
    val capacity: Int,

//    @field: OneToMany
//    val candidates: List<Artist> = emptyList(),

//    @field: OneToOne
//    val winner: Artist? = null

)

@Repository
interface CategoryRepository : CoroutineCrudRepository<Category, String>

