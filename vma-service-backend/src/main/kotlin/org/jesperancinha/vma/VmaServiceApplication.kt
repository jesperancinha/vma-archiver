package org.jesperancinha.vma

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
class VmaServiceApplication

fun main(args: Array<String>) {
	runApplication<VmaServiceApplication>(*args)
}
