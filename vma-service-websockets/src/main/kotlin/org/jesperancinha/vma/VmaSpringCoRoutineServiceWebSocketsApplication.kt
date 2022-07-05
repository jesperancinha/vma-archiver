package org.jesperancinha.vma

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import org.jesperancinha.vma.common.service.CategoryService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Primary
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@SpringBootApplication
class VmaSpringCoRoutineServiceWebSocketsApplication(
    private val template: SimpMessagingTemplate,
    private val categoryService: CategoryService
) {
    @Scheduled(fixedDelay = 5000)
    fun update() = CoroutineScope(IO).launch {
        template.convertAndSend("/topic/vma", categoryService.findAll().toList())
    }
}

fun main(args: Array<String>) {
    runApplication<VmaSpringCoRoutineServiceWebSocketsApplication>(*args)
}

