package org.jesperancinha.vma.vmaservice.config

import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * Created by jofisaes on 14/11/2021
 */
@Configuration
class WebConfig  {
    @Bean
    fun hazelcastInstance(): HazelcastInstance {
        val worldConfig = Config()
        worldConfig.clusterName = "vma-world"
        return Hazelcast.newHazelcastInstance(worldConfig)
    }

}