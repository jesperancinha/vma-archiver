package org.jesperancinha.vma

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan

@SpringBootApplication
@ConfigurationPropertiesScan
class VmaServiceEventListenerLauncher {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(VmaServiceEventListenerLauncher::class.java, *args)
        }
    }
}
