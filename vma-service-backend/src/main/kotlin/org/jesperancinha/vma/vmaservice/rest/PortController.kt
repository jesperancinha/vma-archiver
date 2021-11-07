package org.jesperancinha.vma.vmaservice.rest

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("welcome")
class PortController(
    @Value("\${server.port}")
    private val port: Int
) {

    @GetMapping
    fun getWelcomeMessage(): String = "Welcome to the VMA Voting System Test App! This one is running on port $port"
}