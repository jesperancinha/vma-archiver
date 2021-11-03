package org.jesperancinha.vma.vmaservice.domain

import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository


interface BandRepository : CoroutineCrudRepository<Band, String> {

}


@Table
data class Band(
    val name:String
)