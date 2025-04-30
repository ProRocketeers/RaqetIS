package com.prorocketeers.raqetis.config

import org.flywaydb.core.Flyway
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class FlywayConfig {

    @Bean
    @Profile("development")
    fun resetDbOnStart(flyway: Flyway): CommandLineRunner {
        return CommandLineRunner {
            println("Cleaning and migrating database with Flyway...")
            flyway.clean()
            flyway.migrate()
            println(" Flyway migration completed.")
        }
    }
}
