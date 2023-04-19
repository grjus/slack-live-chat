package com.grjus.slack.chat.model

data class PostMessageCommand(
        val text: String,
        val channelName: String,
        val ts: String?,
        val userName: String?
) {
}