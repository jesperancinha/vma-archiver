package org.jesperancinha.vma.listener.kafka

import kotlinx.coroutines.runBlocking
import org.jesperancinha.vma.common.domain.kafka.VoteCategoryArtist
import org.jesperancinha.vma.common.domain.kafka.VotingCategoryArtistRepository
import org.jesperancinha.vma.common.domain.kafka.VotingCategorySongRepository
import org.jesperancinha.vma.common.dto.ArtistVotingDto
import org.jesperancinha.vma.common.dto.ArtistVotingEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.*

@Component
class VotingRequestHandler(
    private val votingCategoryArtistRepository: VotingCategoryArtistRepository,
    private val votingCategorySongRepository: VotingCategorySongRepository,
    private val kafkaPublisher: VotingRequestPublisher
) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    fun handleCreateVoteRequest(request: ArtistVotingDto): Mono<Unit> {
        val votingId: String = createVotingId()
        val vote = VoteCategoryArtist()
        return runBlocking { votingCategoryArtistRepository.save(vote) }
            .let {
                kafkaPublisher.publishVotingEvent(
                    VotingRequestPublisher.generateMessageKey(),
                    ArtistVotingEvent()
                )
            }
            .map { }
            .doOnError { logger.error("Exception while trying to create a new user", it) }
    }

    private fun createVotingId(): String {
        return UUID.randomUUID().toString()
    }
}