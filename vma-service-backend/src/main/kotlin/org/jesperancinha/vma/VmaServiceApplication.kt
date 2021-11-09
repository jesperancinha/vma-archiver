package org.jesperancinha.vma

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VmaServiceApplication

fun main(args: Array<String>) {
	runApplication<VmaServiceApplication>(*args)
}
