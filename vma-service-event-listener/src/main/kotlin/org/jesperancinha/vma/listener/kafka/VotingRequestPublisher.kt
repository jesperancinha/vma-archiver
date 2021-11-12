package org.jesperancinha.vma.listener.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.requests.VoteRequest
import org.jesperancinha.vma.common.dto.ArtistVotingEvent
import org.jesperancinha.vma.listener.config.VotingKafkaConfigProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kafka.sender.KafkaSender
import reactor.kafka.sender.SenderOptions
import java.util.UUID

@Component
class VotingRequestPublisher(private val kafkaConfigProperties: VotingKafkaConfigProperties) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    companion object {
        fun generateMessageKey(): String {
            return UUID.randomUUID().toString()
        }
    }
}