package org.jesperancinha.vma.vmaservice.rest

import org.jesperancinha.vma.dto.ArtistVotingDto
import org.jesperancinha.vma.dto.SongVotingDto
import org.jesperancinha.vma.dto.VotingId
import org.jesperancinha.vma.vmaservice.services.VotingService
import org.springframework.http.MediaType
import org.springframework.http.ResponseCookie
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("voting")
class VotingController(
    val votingService: VotingService
) {
    @PostMapping("/artist")
    fun postArtistVote(
        @RequestBody artistVotingDto: ArtistVotingDto,
        @CookieValue("votingId") votingKey: String
    ) = votingService.castArtistVote(votingKey, artistVotingDto)

    @PostMapping("/song")
    fun postSongVote(
        @RequestBody songVotingDto: SongVotingDto,
        @CookieValue("votingId") votingKey: String
    ) = votingService.castSongVote(votingKey, songVotingDto)

    @PostMapping("/count")
    suspend fun postStartRecount() = votingService.countVotes()

    @GetMapping("/artist/{idc}/{ida}")
    suspend fun getArtistVotingResults(
        @PathVariable idc: String,
        @PathVariable ida: String
    ) = votingService.getArtistVotingResults(idc, ida)

    @GetMapping("/song/{idc}/{ids}")
    suspend fun getSongVotingResults(
        @PathVariable idc: String,
        @PathVariable ids: String
    ) = votingService.getSongVotingResults(idc, ids)

    @GetMapping(path = ["/open"], produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun open(
        @CookieValue("votingId") votingKey: String?, response: ServerHttpResponse
    ): VotingId =
        VotingId(id = votingKey
            ?.let {
                votingService.addVotingKeyToCache(it)
                it
            }
            ?: run {
                val votingId = UUID.randomUUID().toString()
                response.addCookie(ResponseCookie.from("votingId", votingId).build())
                votingService.addVotingKeyToCache(votingId)
                votingId
            })

}