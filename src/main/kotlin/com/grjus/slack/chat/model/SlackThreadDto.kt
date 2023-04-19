package com.grjus.slack.chat.model

import java.util.UUID

data class SlackThreadDto(
        val uuid: UUID,
        val channelName: String,
        val channelId: String,
        val threadTs: String,
        val text: String,
)