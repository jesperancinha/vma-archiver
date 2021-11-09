package org.jesperancinha.vma.common.domain.kafka

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

class CreateVoteRequest {

}

class CreatedVoteEvent {

}

class Vote {

}

interface VotingRepository : CoroutineCrudRepository<Vote, String>

