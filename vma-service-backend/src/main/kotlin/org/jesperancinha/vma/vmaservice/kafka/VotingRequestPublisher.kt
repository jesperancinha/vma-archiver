package org.jesperancinha.vma.vmaservice.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.jesperancinha.vma.common.dto.ArtistVotingDto
import org.jesperancinha.vma.common.dto.ArtistVotingEvent
import org.jesperancinha.vma.common.dto.SongVotingDto
import org.jesperancinha.vma.vmaservice.config.VotingKafkaConfigProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kafka.sender.KafkaSender
import reactor.kafka.sender.SenderOptions
import java.util.*

@Component
class VotingRequestPublisher(private val kafkaConfigProperties: VotingKafkaConfigProperties) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    private val producerProps: Map<String, String> = mapOf(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaConfigProperties.broker,
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to kafkaConfigProperties.serializer,
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to kafkaConfigProperties.serializer,
            "schema.registry.url" to kafkaConfigProperties.schemaRegistryUrl
    )

    private val voteArtistRequestSenderOptions: SenderOptions<String, ArtistVotingDto> = SenderOptions.create(producerProps)
    private val voteArtistRequestRequestKafkaSender: KafkaSender<String, ArtistVotingDto> =
        KafkaSender.create(voteArtistRequestSenderOptions)

    private val voteRequestSenderOptions: SenderOptions<String, SongVotingDto> = SenderOptions.create(producerProps)
    private val voteSongRequestRequestKafkaSender: KafkaSender<String, SongVotingDto> =
        KafkaSender.create(voteRequestSenderOptions)

    private val voteCreatedEventSenderOptions: SenderOptions<String, ArtistVotingEvent> =
        SenderOptions.create(producerProps)
    private val voteCreatedEventKafkaSender: KafkaSender<String, ArtistVotingEvent> =
        KafkaSender.create(voteCreatedEventSenderOptions)

    fun publishArtistVote(key: String, artistVotingDto: ArtistVotingDto): Mono<Void> {
        val producerRecord: ProducerRecord<String, ArtistVotingDto> =
            ProducerRecord(kafkaConfigProperties.createVoteRequestTopic, key, artistVotingDto)

        return voteArtistRequestRequestKafkaSender.createOutbound()
            .send(Mono.just(producerRecord))
            .then()
            .doOnSuccess { logger.info("Vote Created with id $key") }
    }

    fun publishSongVote(key: String, songVotingDto: SongVotingDto): Mono<Void> {
        val producerRecord: ProducerRecord<String, SongVotingDto> =
            ProducerRecord(kafkaConfigProperties.createVoteRequestTopic, key, songVotingDto)

        return voteSongRequestRequestKafkaSender.createOutbound()
            .send(Mono.just(producerRecord))
            .then()
            .doOnSuccess { logger.info("Vote Created with id $key") }
    }

    fun publishVotingEvent(key: String, value: ArtistVotingEvent): Mono<Void> {
        val producerRecord: ProducerRecord<String, ArtistVotingEvent> =
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