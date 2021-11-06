package org.jesperancinha.vma.vmaservice.domain

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Table
@Entity
data class Category(
    @field: Id @field: GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String = UUID.randomUUID().toString(),

    @OneToMany
    val candidates: List<Artist>
)