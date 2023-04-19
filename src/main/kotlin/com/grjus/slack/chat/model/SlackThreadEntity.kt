package com.grjus.slack.chat.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "slack_thread")
class SlackThreadEntity(
        val channelName: String,
        val channelId: String,
        val threadTs: String,
        val text: String,
        var hiddenAt: LocalDate? = null,

) {
    @Id
    val uuid = UUID.randomUUID()
}

fun SlackThreadEntity.toDto() = SlackThreadDto(
        channelId = this.channelId,
        channelName = this.channelName,
        text = this.text,
        threadTs = this.threadTs,
        uuid = this.uuid
)