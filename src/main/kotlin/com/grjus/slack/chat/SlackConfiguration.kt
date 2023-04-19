package com.grjus.slack.chat

import com.slack.api.Slack
import com.slack.api.methods.MethodsClient
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SlackConfiguration(
        @Value("\${slack-api.token:}")
        private val token: String,
) {

    @Bean
    fun slackMethods():MethodsClient{
       return Slack.getInstance().methods(token)
    }
}