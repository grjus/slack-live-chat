package com.grjus.slack.chat.service

import com.grjus.slack.chat.model.GetThreadMessagesCommand
import com.grjus.slack.chat.model.PostMessageCommand
import com.grjus.slack.chat.model.SlackThreadDto
import com.grjus.slack.chat.model.SlackThreadEntity
import com.grjus.slack.chat.model.SlackThreadRepository
import com.grjus.slack.chat.model.toDto
import com.slack.api.methods.MethodsClient
import com.slack.api.methods.response.chat.ChatPostMessageResponse
import com.slack.api.methods.response.conversations.ConversationsRepliesResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class LiveChatService(
        private val slackMethods:MethodsClient,
        private val slackThreadRepository: SlackThreadRepository

) {

    @Transactional
    fun postMessage(command: PostMessageCommand): ChatPostMessageResponse? {
        val postMessage = slackMethods.chatPostMessage {
            it.channel(command.channelName)
            it.text(command.text)
            it.threadTs(command.ts)
            it.username(command.userName)
        }
        if(command.ts == null) {
            slackThreadRepository.save(SlackThreadEntity(command.channelName,
                    postMessage.channel, postMessage.ts, command.text.substring(0,100)))
        }

        return postMessage

    }

    fun getMessagesForThread(command:GetThreadMessagesCommand): ConversationsRepliesResponse? {
        return slackMethods.conversationsReplies {
            it.channel(command.channel)
            it.ts(command.ts)
            it.inclusive(command.inclusive)
            it.limit(command.limit)
        }
    }

    @Transactional
    fun getMessageThreads(): List<SlackThreadDto> {
        return slackThreadRepository.findAllByHiddenAtNull().map { it.toDto() }
    }


    @Transactional
    fun deleteThread(threadTs:String) {
        val slackMessageThread = slackThreadRepository.findByThreadTs(threadTs)
        slackMessageThread.let { it.hiddenAt = LocalDate.now() }
    }




}