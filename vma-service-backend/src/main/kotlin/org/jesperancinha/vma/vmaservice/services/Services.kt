package org.jesperancinha.vma.vmaservice.services

import com.hazelcast.core.HazelcastInstance
import kotlinx.coroutines.reactor.mono
import org.jesperancinha.vma.common.domain.*
import org.jesperancinha.vma.common.dto.ArtistVotingDto
import org.jesperancinha.vma.common.dto.SongVotingDto
import org.jesperancinha.vma.vmaservice.kafka.VotingRequestPublisher
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * Created by jofisaes on 06/07/2022
 */

@Service
class VotingService(
    private val votingRequestPublisher: VotingRequestPublisher,
    private val categoryArtistRepository: CategoryArtistRepository,
    private val categorySongRepository: CategorySongRepository,
    private val votingCategoryArtistRepository: VotingCategoryArtistRepository,
    private val votingCategorySongRepository: VotingCategorySongRepository,
    hazelcastInstance: HazelcastInstance
) {
    private val cache: MutableMap<String, VotingStatus> = hazelcastInstance.getMap("vma-cache")

    suspend fun castArtistVote(voterKey: String, artistVotingDto: ArtistVotingDto): Mono<Void> =
        cache[voterKey]?.votedOff?.let { voted ->
            if (!voted.contains(artistVotingDto.idC)) {
                return votingRequestPublisher.publishArtistVote(
                    key = voterKey,
                    artistVotingDto = artistVotingDto.copy(userId = voterKey)
                ).and(mono { voted.add(artistVotingDto.idC) })
            }
        }.let { Mono.empty() }

    suspend fun castSongVote(voterKey: String, songVotingDto: SongVotingDto): Mono<Void> =
        cache[voterKey]?.votedOff?.let { voted ->
            if (!voted.contains(songVotingDto.idC)) {
                return votingRequestPublisher.publishSongVote(
                    key = voterKey,
                    songVotingDto = songVotingDto.copy(userId = voterKey)
                ).and(mono { voted.add(songVotingDto.idC) })
            }
        }.let { Mono.empty() }


    suspend fun countVotes() {
        categoryArtistRepository.findAll().collect { artistCategory ->
            val countByCategoryId = votingCategoryArtistRepository.findCountByCategoryId(artistCategory.idA)
            if (artistCategory.voteCount == 0L) {
                categoryArtistRepository.save(
                    artistCategory.copy(
                        voteCount = countByCategoryId?.toLong() ?: 0,
                        updates = artistCategory.updates + 1
                    )
                )
            }

        }
        categorySongRepository.findAll().collect { songCategory ->
            val countByCategoryId = votingCategorySongRepository.findCountByCategoryId(songCategory.idS)
            if (songCategory.voteCount == 0L) {
                categorySongRepository.save(
                    songCategory.copy(
                        voteCount = countByCategoryId?.toLong() ?: 0,
                        updates = songCategory.updates + 1
                    )
                )
            }
        }
    }

    suspend fun addVotingKeyToCache(votingId: String) {
        if (!cache.containsKey(votingId)) {
            cache[votingId] = VotingStatus(votingId)
        }
    }

    suspend fun getArtistVotingResults(idc: String, ida: String): Long {
        val cat = categoryArtistRepository.findByCategoryIdAndArtistId(idc, ida)
        return cat.votes + cat.voteCount
    }

    suspend fun getSongVotingResults(idc: String, ids: String): Long {
        val cat = categorySongRepository.findByCategoryIdAndSongId(idc, ids)
        return cat.votes + cat.voteCount
    }
}
