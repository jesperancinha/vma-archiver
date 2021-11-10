package org.jesperancinha.vma.vmaservice.kafka

import org.jesperancinha.vma.common.domain.kafka.VoteCategoryArtist
import org.jesperancinha.vma.common.domain.kafka.VotingCategoryArtistRepository
import org.jesperancinha.vma.common.domain.kafka.VotingCategorySongRepository
import org.jesperancinha.vma.common.dto.VotingDto
import org.jesperancinha.vma.common.dto.VotingEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.UUID

@Component
class VotingRequestHandler(
    private val votingCategoryArtistRepository: VotingCategoryArtistRepository,
    private val votingCategorySongRepository: VotingCategorySongRepository,
    private val kafkaPublisher: VotingRequestPublisher
) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    suspend fun handleCreateVoteRequest(request: VotingDto): Mono<Unit> {
        val votingId: String = createVotingId()
        val vote = VoteCategoryArtist()
        return votingCategoryArtistRepository.save(vote)
            .let {
                kafkaPublisher.publishVotingEvent(
                    VotingRequestPublisher.generateMessageKey(),
                    VotingEvent()
                )
            }
            .map { }
            .doOnError { logger.error("Exception while trying to create a new user", it) }
    }

    private fun createVotingId(): String {
        return UUID.randomUUID().toString()
    }
}