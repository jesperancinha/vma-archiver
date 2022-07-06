package org.jesperancinha.vma

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.jesperancinha.vma.dto.CategoryDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject


@SpringBootApplication
@EnableScheduling
class VmaSpringCoRoutineServiceWebSocketsApplication(
    private val template: SimpMessagingTemplate,
    private val categoryService: CategoryService
) {
    @Scheduled(fixedDelay = 5000)
    fun update() = CoroutineScope(IO).launch {
        template.convertAndSend("/topic/vma", categoryService.findAll())
    }
}

fun main(args: Array<String>) {
    runApplication<VmaSpringCoRoutineServiceWebSocketsApplication>(*args)
}

@Configuration
class SocketsConfiguration{
    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate? {
        return builder.build()
    }
}

@Service
class CategoryService(
    val restTemplate: RestTemplate
) {

    @Value("\${vma.reactive.endpoint}")
    lateinit var vmaBackEndEndpoint: String

    fun findAll(): List<CategoryDto> = restTemplate.getForObject<List<CategoryDto>>(
        "${vmaBackEndEndpoint}/api/vma/registry", List::class.java)
}