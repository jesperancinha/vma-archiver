package org.jesperancinha.vma.listener.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.requests.VoteRequest
import org.jesperancinha.vma.common.dto.VotingEvent
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

    private val producerProps: Map<String, String> = mapOf(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaConfigProperties.broker,
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to kafkaConfigProperties.serializer,
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to kafkaConfigProperties.serializer,
        "schema.registry.url" to kafkaConfigProperties.schemaRegistryUrl
    )

    private val voteRequestSenderOptions: SenderOptions<String, VoteRequest> = SenderOptions.create(producerProps)
    private val voteRequestRequestKafkaSender: KafkaSender<String, VoteRequest> =
        KafkaSender.create(voteRequestSenderOptions)

    private val voteCreatedEventSenderOptions: SenderOptions<String, VotingEvent> =
        SenderOptions.create(producerProps)
    private val voteCreatedEventKafkaSender: KafkaSender<String, VotingEvent> =
        KafkaSender.create(voteCreatedEventSenderOptions)

    fun publishVote(key: String, value: VoteRequest): Mono<Void> {
        val producerRecord: ProducerRecord<String, VoteRequest> =
            ProducerRecord(kafkaConfigProperties.voteCreatedEventTopic, key, value)

        return voteRequestRequestKafkaSender.createOutbound()
            .send(Mono.just(producerRecord))
            .then()
            .doOnSuccess { logger.info("Vote Created with id $key") }
    }

    fun publishVotingEvent(key: String, value: VotingEvent): Mono<Void> {
        val producerRecord: ProducerRecord<String, VotingEvent> =
            ProducerRecord(kafkaConfigProperties.voteCreatedEventTopic, key, value)

        return voteCreatedEventKafkaSender.createOutbound()
            .send(Mono.just(producerRecord))
            .then()
            .doOnSuccess { logger.info("Registered Vote with id $key") }
    }

    companion object {
        fun generateMessageKey(): String {
            return UUID.randomUUID().toString()
        }
    }
}