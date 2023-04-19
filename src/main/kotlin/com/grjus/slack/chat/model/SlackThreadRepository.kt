package com.grjus.slack.chat.model

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SlackThreadRepository: JpaRepository<SlackThreadEntity, UUID> {

    fun findAllByHiddenAtNull():List<SlackThreadEntity>
    fun findByThreadTs(threadTs: String): SlackThreadEntity

}