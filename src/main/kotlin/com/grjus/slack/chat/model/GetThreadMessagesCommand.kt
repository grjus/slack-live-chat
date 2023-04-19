package com.grjus.slack.chat.model

data class GetThreadMessagesCommand(
        val channel: String,
        val ts: String,
        val limit: Int,
        val inclusive: Boolean
)