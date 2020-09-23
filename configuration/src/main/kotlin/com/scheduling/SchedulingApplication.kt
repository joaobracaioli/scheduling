package com.scheduling

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.scheduling.configuration"])

class SchedulingApplication

fun main(args: Array<String>) {
	runApplication<SchedulingApplication>(*args)
}
