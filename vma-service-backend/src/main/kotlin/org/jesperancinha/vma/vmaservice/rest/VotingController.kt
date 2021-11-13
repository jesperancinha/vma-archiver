package org.jesperancinha.vma.vmaservice.rest

import org.jesperancinha.vma.common.dto.ArtistVotingDto
import org.jesperancinha.vma.common.dto.SongVotingDto
import org.jesperancinha.vma.vmaservice.service.VotingService
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("voting")
class VotingController(
    val votingService: VotingService
) {
    @PostMapping("/artist")
    suspend fun postArtistVote(@RequestBody artistVotingDto: ArtistVotingDto, @CookieValue("votingId") votingKey: String) = votingService.castArtistVote(votingKey, artistVotingDto)

    @PostMapping("/song")
   suspend fun postSongVote(@RequestBody songVotingDto: SongVotingDto, @CookieValue("votingId") votingKey: String) = votingService.castSongVote(votingKey, songVotingDto)

    @PostMapping("/count")
  suspend  fun postStartRecount() = votingService.countVotes()
}