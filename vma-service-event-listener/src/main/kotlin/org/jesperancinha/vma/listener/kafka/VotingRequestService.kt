package org.jesperancinha.vma.listener.kafka

import org.apache.avro.generic.GenericData.Record
import org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG
import org.jesperancinha.vma.listener.config.VotingKafkaConfigProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import reactor.core.Disposable
import reactor.kafka.receiver.KafkaReceiver
import reactor.kafka.receiver.ReceiverOptions
import java.time.Duration
import java.util.Collections


@Component
class VotingRequestService(
    votingKafkaConfigProperties: VotingKafkaConfigProperties,
    private val createUserRequestHandler: VotingRequestHandler
) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    private val consumerProps: Map<String, Any> = mapOf(
        BOOTSTRAP_SERVERS_CONFIG to votingKafkaConfigProperties.broker,
        GROUP_ID_CONFIG to "create-vote-request-v1",
        KEY_DESERIALIZER_CLASS_CONFIG to votingKafkaConfigProperties.deserializer,
        VALUE_DESERIALIZER_CLASS_CONFIG to votingKafkaConfigProperties.deserializer,
        AUTO_OFFSET_RESET_CONFIG to "earliest",
        ENABLE_AUTO_COMMIT_CONFIG to false,
        "schema.registry.url" to votingKafkaConfigProperties.schemaRegistryUrl,
    )

    private val receiverOptions: ReceiverOptions<String, Record> = ReceiverOptions
        .create<String, Record>(consumerProps)
        .commitInterval(Duration.ZERO)
        .commitBatchSize(0)
        .subscription(Collections.singleton(votingKafkaConfigProperties.createVoteRequestTopic))

    @Bean
    private fun votingRequestListener(): Disposable {
        return KafkaReceiver.create(receiverOptions)
            .receive()
            .concatMap { record ->
                createUserRequestHandler
                    .handleCreateVoteRequest(record.value())
                    .then(record.receiverOffset().commit())
                    .doOnError {
                        logger.error(
                            "Error while creating Vote",
                            it
                        )
                    }
            }
            .subscribe()
    }
}