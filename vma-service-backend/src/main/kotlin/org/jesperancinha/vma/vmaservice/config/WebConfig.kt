package org.jesperancinha.vma.vmaservice.config

import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


/**
 * Created by jofisaes on 14/11/2021
 */
@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.enableSimpleBroker("/topic")
        config.setApplicationDestinationPrefixes("/app")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry
            .addEndpoint("/broker")
            .setAllowedOrigins("http://localhost:4200")
            .withSockJS()
    }

    @Bean
    fun hazelcastInstance(): HazelcastInstance {
        val worldConfig = Config()
        worldConfig.clusterName = "vma-world"
        return Hazelcast.newHazelcastInstance(worldConfig)
    }

}