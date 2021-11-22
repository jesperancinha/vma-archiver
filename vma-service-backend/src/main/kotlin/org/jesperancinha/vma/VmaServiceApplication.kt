package org.jesperancinha.vma

import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableR2dbcRepositories
@EnableScheduling
@ConfigurationPropertiesScan
class VmaServiceApplication {
    @Bean
    @Profile("starter")
    fun initializer(@Qualifier("connectionFactory") connectionFactory: ConnectionFactory): ConnectionFactoryInitializer? {
        val initializer = ConnectionFactoryInitializer()
        initializer.setConnectionFactory(connectionFactory)
        val resource = ResourceDatabasePopulator(ClassPathResource("schema.sql"))
        initializer.setDatabasePopulator(resource)
        return initializer
    }
}

fun main(args: Array<String>) {
    runApplication<VmaServiceApplication>(*args)
}