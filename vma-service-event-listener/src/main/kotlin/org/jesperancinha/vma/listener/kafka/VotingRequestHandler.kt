package org.jesperancinha.vma.listener.kafka

import kotlinx.coroutines.reactor.mono
import org.apache.avro.generic.GenericData.Record
import org.jesperancinha.vma.common.domain.kafka.VoteCategoryArtist
import org.jesperancinha.vma.common.domain.kafka.VoteCategorySong
import org.jesperancinha.vma.common.domain.kafka.VotingCategoryArtistRepository
import org.jesperancinha.vma.common.domain.kafka.VotingCategorySongRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class VotingRequestHandler(
    private val votingCategoryArtistRepository: VotingCategoryArtistRepository,
    private val votingCategorySongRepository: VotingCategorySongRepository,
) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    fun handleCreateVoteRequest(request: Record): Mono<Unit> {

        request.schema.name.let { name ->
            when (name) {
                "ArtistVotingDto" -> {
                    val vote = VoteCategoryArtist(
                        userId = request.get(0).toString(),
                        idC = request.get(1).toString(),
                        idA = request.get(2).toString()
                    )
                    votingCategoryArtistRepository.saveAll(listOf(vote))
                }
                else -> {
                    val vote = VoteCategorySong(
                        userId = request.get(0).toString(),
                        idC = request.get(1).toString(),
                        idS = request.get(2).toString()
                    )
                    votingCategorySongRepository.saveAll(listOf(vote))
                }
            }

        }

        return mono { }.doOnError { logger.error("Exception while trying to create a new user", it) }
    }
}