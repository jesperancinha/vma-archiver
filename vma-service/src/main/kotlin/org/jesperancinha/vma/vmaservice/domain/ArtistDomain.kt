package org.jesperancinha.vma.vmaservice.domain

import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


interface BandRepository : CoroutineCrudRepository<Band, String>

@Table
@Entity
data class Band(
    @field: Id @field: GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String = UUID.randomUUID().toString(),
    val name: String
)

@Table
@Entity
data class Artist(
    @field: Id @field: GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String = UUID.randomUUID().toString(),
    val name: String
)

@Table
@Entity
data class Song(
    @field: Id @field: GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String = UUID.randomUUID().toString(),
    val name: String = UUID.randomUUID().toString(),
    val year: Int,
    val month: Int,
    val day: Int
)