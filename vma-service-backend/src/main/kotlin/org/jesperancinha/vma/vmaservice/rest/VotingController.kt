package org.jesperancinha.vma.vmaservice.rest

import org.jesperancinha.vma.common.dto.VotingDto
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("voting")
class VotingController {

    @PostMapping
    fun postVote(@RequestBody votingDto: VotingDto, @CookieValue("votingId") string: String){
        
    }

}