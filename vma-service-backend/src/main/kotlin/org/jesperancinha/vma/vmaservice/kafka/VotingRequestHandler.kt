package org.jesperancinha.vma.vmaservice.kafka

import org.jesperancinha.vma.common.domain.kafka.CreateVoteRequest
import org.jesperancinha.vma.common.domain.kafka.CreatedVoteEvent
import org.jesperancinha.vma.common.domain.kafka.VoteCategoryArtist
import org.jesperancinha.vma.common.domain.kafka.VotingRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.UUID

@Component
class VotingRequestHandler(
    private val votingRepository: VotingRepository,
    private val kafkaPublisher: VotingRequestPublisher
) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    suspend fun handleCreateVoteRequest(request: CreateVoteRequest): Mono<Unit> {
        val votingId: String = createVotingId()
        val vote = VoteCategoryArtist()
        return votingRepository.save(vote)
            .let {
                kafkaPublisher.publishCreatedVoteEvent(
                    VotingRequestPublisher.generateMessageKey(),
                    CreatedVoteEvent()
                )
            }
            .map { }
            .doOnError { logger.error("Exception while trying to create a new user", it) }
    }

    private fun createVotingId(): String {
        return UUID.randomUUID().toString()
    }
}