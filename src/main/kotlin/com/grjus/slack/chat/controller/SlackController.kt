package com.grjus.slack.chat.controller

import com.grjus.slack.chat.model.GetThreadMessagesCommand
import com.grjus.slack.chat.model.PostMessageCommand
import com.grjus.slack.chat.model.SlackThreadDto
import com.grjus.slack.chat.service.LiveChatService
import com.slack.api.methods.response.chat.ChatPostMessageResponse
import com.slack.api.methods.response.conversations.ConversationsRepliesResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class SlackController(private val liveChatService: LiveChatService) {

    @GetMapping("/thread-messages")
    fun getThreadMessages(@RequestBody command: GetThreadMessagesCommand): ConversationsRepliesResponse? {
        return liveChatService.getMessagesForThread(command)
    }

    @PostMapping("/message")
    fun postMessage(@RequestBody command: PostMessageCommand): ChatPostMessageResponse? {
        return liveChatService.postMessage(command)
    }

    @GetMapping("/threads")
    fun getMessages():List<SlackThreadDto>{
        return liveChatService.getMessageThreads()
    }

    @PostMapping("/threads/{ts}")
    fun deleteThread(@PathVariable("ts") ts:String) {
        liveChatService.deleteThread(ts)
    }

}