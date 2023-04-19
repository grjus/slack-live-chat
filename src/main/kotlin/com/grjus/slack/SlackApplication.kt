package com.grjus.slack

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SlackApplication

fun main(args: Array<String>) {
	runApplication<SlackApplication>(*args)
}
