package org.jesperancinha.vma.vmaservice.rest

import org.jesperancinha.vma.common.dto.ArtistVotingDto
import org.jesperancinha.vma.common.dto.SongVotingDto
import org.jesperancinha.vma.common.dto.VotingId
import org.jesperancinha.vma.vmaservice.service.VotingService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("voting")
class VotingController(
    val votingService: VotingService
) {
    @PostMapping("/artist")
    suspend fun postArtistVote(
        @RequestBody artistVotingDto: ArtistVotingDto,
        @CookieValue("votingId") votingKey: String
    ) = votingService.castArtistVote(votingKey, artistVotingDto)

    @PostMapping("/song")
    suspend fun postSongVote(
        @RequestBody songVotingDto: SongVotingDto,
        @CookieValue("votingId") votingKey: String
    ) = votingService.castSongVote(votingKey, songVotingDto)

    @PostMapping("/count")
    suspend fun postStartRecount() = votingService.countVotes()

    @GetMapping(path = ["/open"], produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun open(@CookieValue("votingId") votingKey: String?, response: HttpServletResponse): VotingId {
        return VotingId(id = votingKey
            ?.let { votingKey }
            ?: run {
                val votingId = UUID.randomUUID().toString()
                response.addCookie(Cookie("votingId", votingId))
                votingService.addVotingKeyToCache(votingId)
                votingId
            })
    }
}