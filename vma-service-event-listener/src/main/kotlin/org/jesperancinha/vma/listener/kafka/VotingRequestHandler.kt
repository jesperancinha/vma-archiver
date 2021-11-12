package org.jesperancinha.vma.listener.kafka

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
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
import kotlin.coroutines.CoroutineContext

@Component
class VotingRequestHandler(
    private val votingCategoryArtistRepository: VotingCategoryArtistRepository,
    private val votingCategorySongRepository: VotingCategorySongRepository,
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
                  }
                  else -> {
                      val vote = VoteCategorySong(
                          userId = request.get(0).toString(),
                          idC = request.get(1).toString(),
                          idS = request.get(2).toString()
                      )
                      votingCategorySongRepository.save(vote)
                  }
              }
          }
        }

        return mono { }.doOnError { logger.error("Exception while trying to create a new user", it) }
    }
}