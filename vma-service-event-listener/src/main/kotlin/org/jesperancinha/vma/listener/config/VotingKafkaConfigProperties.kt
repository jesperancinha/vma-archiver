package org.jesperancinha.vma.listener.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "application.kafka")
data class VotingKafkaConfigProperties(
    var broker: String = "",
    var schemaRegistryUrl: String = "",
    var createUserRequestTopic: String = "",
    var userCreatedEventTopic: String = "",
    var serializer: String = "",
    var deserializer: String = ""
)