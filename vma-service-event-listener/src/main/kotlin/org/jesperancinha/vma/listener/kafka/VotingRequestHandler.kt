package org.jesperancinha.vma.listener.kafka

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactor.mono
import org.apache.avro.generic.GenericData.Record
import org.jesperancinha.vma.common.domain.CategoryArtistRepository
import org.jesperancinha.vma.common.domain.CategorySongRepository
import org.jesperancinha.vma.common.domain.VoteCategoryArtist
import org.jesperancinha.vma.common.domain.VoteCategorySong
import org.jesperancinha.vma.common.domain.VotingCategoryArtistRepository
import org.jesperancinha.vma.common.domain.VotingCategorySongRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class VotingRequestHandler(
    private val votingCategoryArtistRepository: VotingCategoryArtistRepository,
    private val votingCategorySongRepository: VotingCategorySongRepository,
    private val categoryArtistRepository: CategoryArtistRepository,
    private val categorySongRepository: CategorySongRepository
) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    fun handleCreateVoteRequest(request: Record): Mono<Unit> {
        request.schema.name.let { name ->
            CoroutineScope(IO).launch {
                when (name) {
                    "ArtistVotingDto" -> {
                        val vote = VoteCategoryArtist(
                            userId = request.get(0).toString(),
                            idC = request.get(1).toString(),
                            idA = request.get(2).toString()
                        )
                        votingCategoryArtistRepository.save(vote)
                        val category = categoryArtistRepository.findByCategoryIdAndArtistId(vote.idC, vote.idA)
                        categoryArtistRepository.save(
                            category.copy(
                                votes = category.votes + 1,
                                updates = category.updates + 1
                            )
                        )
                    }
                    else -> {
                        val vote = VoteCategorySong(
                            userId = request.get(0).toString(),
                            idC = request.get(1).toString(),
                            idS = request.get(2).toString()
                        )
                        votingCategorySongRepository.save(vote)
                        val category = categorySongRepository.findByCategoryIdAndSongId(vote.idC, vote.idS)
                        categorySongRepository.save(
                            category.copy(
                                votes = category.votes + 1,
                                updates = category.updates + 1
                            )
                        )

                    }
                }
            }
        }
        return mono { }.doOnError { logger.error("Exception while trying to create a new user", it) }
    }
}