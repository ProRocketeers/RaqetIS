package com.prorocketeers.raqetis

import GraphiQlConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(GraphiQlConfiguration::class)
class RaqetisApplication

fun main(args: Array<String>) {
	runApplication<RaqetisApplication>(*args)
}