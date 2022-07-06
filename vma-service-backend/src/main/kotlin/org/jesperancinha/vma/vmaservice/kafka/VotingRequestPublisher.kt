package org.jesperancinha.vma.vmaservice.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.jesperancinha.vma.dto.ArtistVotingDto
import org.jesperancinha.vma.dto.SongVotingDto
import org.jesperancinha.vma.config.VotingKafkaConfigProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kafka.sender.KafkaSender
import reactor.kafka.sender.SenderOptions

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

    private val voteSongRequestSenderOptions: SenderOptions<String, SongVotingDto> = SenderOptions.create(producerProps)
    private val voteSongRequestRequestKafkaSender: KafkaSender<String, SongVotingDto> =
        KafkaSender.create(voteSongRequestSenderOptions)

   suspend fun publishArtistVote(key: String, artistVotingDto: ArtistVotingDto): Mono<Void> {
        val producerRecord: ProducerRecord<String, ArtistVotingDto> =
            ProducerRecord(kafkaConfigProperties.createArtistVoteRequestTopic, key, artistVotingDto)

        return voteArtistRequestRequestKafkaSender.createOutbound()
            .send(Mono.just(producerRecord))
            .then()
            .doOnSuccess { logger.info("Vote Created with id $key") }
    }

   suspend fun publishSongVote(key: String, songVotingDto: SongVotingDto): Mono<Void> {
        val producerRecord: ProducerRecord<String, SongVotingDto> =
            ProducerRecord(kafkaConfigProperties.createSongVoteRequestTopic, key, songVotingDto)

        return voteSongRequestRequestKafkaSender.createOutbound()
            .send(Mono.just(producerRecord))
            .then()
            .doOnSuccess { logger.info("Vote Created with id $key") }
    }
}
