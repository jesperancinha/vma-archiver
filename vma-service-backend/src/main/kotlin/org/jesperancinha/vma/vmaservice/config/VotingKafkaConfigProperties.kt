package org.jesperancinha.vma.vmaservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "application.kafka")
data class VotingKafkaConfigProperties(
    var broker: String = "",
    var schemaRegistryUrl: String = "",
    var createArtisVoteRequestTopic: String = "",
    var createSongVoteRequestTopic: String = "",
    var voteCreatedEventTopic: String = "",
    var serializer: String = "",
    var deserializer: String = ""
)