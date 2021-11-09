package org.jesperancinha.vma.common.domain.kafka

import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class CreateVoteRequest {

}

class CreatedVoteEvent {

}

@Table
@Entity
data class Vote(
    @field: Id @field: GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String = UUID.randomUUID().toString()
)

interface VotingRepository : CoroutineCrudRepository<Vote, String>

