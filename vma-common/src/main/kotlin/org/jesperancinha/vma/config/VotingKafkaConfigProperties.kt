package org.jesperancinha.vma.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "application.kafka")
data class VotingKafkaConfigProperties(
    val broker: String = "",
    val schemaRegistryUrl: String = "",
    val createArtistVoteRequestTopic: String = "",
    val createSongVoteRequestTopic: String = "",
    val voteCreatedEventTopic: String = "",
    val serializer: String = "",
    val deserializer: String = ""
)