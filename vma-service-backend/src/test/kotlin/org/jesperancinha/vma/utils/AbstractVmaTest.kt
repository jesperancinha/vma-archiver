package org.jesperancinha.vma.utils

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container

class TestPostgresSQLContainer(imageName: String) : PostgreSQLContainer<TestPostgresSQLContainer>(imageName)

abstract class AbstractVmaTest {
    companion object {
        @Container
        @JvmField
        val postgreSQLContainer: TestPostgresSQLContainer = TestPostgresSQLContainer("postgres")
            .withUsername("postgres")
            .withPassword("admin")
            .withDatabaseName("vma")


        init {
            postgreSQLContainer.start()
        }

        @DynamicPropertySource
        @JvmStatic
        fun setProperties(registry: DynamicPropertyRegistry) {
            registry.add(
                "spring.datasource.url"
            ) { "jdbc:postgresql://localhost:${postgreSQLContainer.firstMappedPort}/vma" }
        }
    }
}