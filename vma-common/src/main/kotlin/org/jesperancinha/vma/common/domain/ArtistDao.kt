package org.jesperancinha.vma.common.domain

import org.hibernate.Hibernate
import org.jesperancinha.vma.common.dto.CategoryType
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Table
@Entity
data class Band(
    @field: Id @field: GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String = UUID.randomUUID().toString(),
    val name: String

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Band

        return id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name )"
    }
}

@Repository
interface BandRepository : CoroutineCrudRepository<Band, String>

@Table
@Entity
data class Artist(
    @field: Id
    @field: org.springframework.data.annotation.Id
    val idA: String = UUID.randomUUID().toString(),

    val name: String,

    val type: CategoryType,

    val created: Long? = null,

    val updates: Int = -1

) : Persistable<String> {
    override fun isNew(): Boolean = updates < 0

    override fun getId(): String = idA

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Artist

        return idA == other.idA
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name )"
    }
}

@Repository
interface ArtistRepository : CoroutineCrudRepository<Artist, String>