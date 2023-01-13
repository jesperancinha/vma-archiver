package org.jesperancinha.vma.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "application.kafka")
data class VotingKafkaConfigProperties(
    var broker: String = "",
    var schemaRegistryUrl: String = "",
    var createArtistVoteRequestTopic: String = "",
    var createSongVoteRequestTopic: String = "",
    var voteCreatedEventTopic: String = "",
    var serializer: String = "",
    var deserializer: String = ""
)