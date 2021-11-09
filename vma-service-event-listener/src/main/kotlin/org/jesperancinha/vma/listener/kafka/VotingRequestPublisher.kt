package org.jesperancinha.vma.listener.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.jesperancinha.vma.common.domain.kafka.CreateVoteRequest
import org.jesperancinha.vma.common.domain.kafka.CreatedVoteEvent
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

    private val voteRequestSenderOptions: SenderOptions<String, CreateVoteRequest> = SenderOptions.create(producerProps)
    private val voteRequestRequestKafkaSender: KafkaSender<String, CreateVoteRequest> =
        KafkaSender.create(voteRequestSenderOptions)

    private val voteCreatedEventSenderOptions: SenderOptions<String, CreatedVoteEvent> =
        SenderOptions.create(producerProps)
    private val voteCreatedEventKafkaSender: KafkaSender<String, CreatedVoteEvent> =
        KafkaSender.create(voteCreatedEventSenderOptions)

    fun publishVote(key: String, value: CreateVoteRequest): Mono<Void> {
        val producerRecord: ProducerRecord<String, CreateVoteRequest> =
            ProducerRecord(kafkaConfigProperties.voteCreatedEventTopic, key, value)

        return voteRequestRequestKafkaSender.createOutbound()
            .send(Mono.just(producerRecord))
            .then()
            .doOnSuccess { logger.info("Vote Created with id $key") }
    }

    fun publishCreatedVoteEvent(key: String, value: CreatedVoteEvent): Mono<Void> {
        val producerRecord: ProducerRecord<String, CreatedVoteEvent> =
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