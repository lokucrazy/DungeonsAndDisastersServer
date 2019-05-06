package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.Messenger
import com.mudndcapstone.server.services.ChatService
import com.mudndcapstone.server.services.SessionService
import com.mudndcapstone.server.utils.Exceptions
import com.mudndcapstone.server.utils.PaginationHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

@RestController
class ChatController {

    @Autowired ChatService chatService
    @Autowired SessionService sessionService

    @PutMapping("/chats/{chatId}")
    ResponseEntity<List<String>> addMessage(@PathVariable String chatId, @Valid @RequestBody Messenger messenger) {
        Chat chat = chatService.getChatById(chatId)
        if (!chat) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.CHAT_NOT_FOUND_EXCEPTION)

        chat = chatService.addMessage(chat, messenger.body)
        if (!chat) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.MESSAGE_NOT_ADDED_EXCEPTION)

        List<String> messages = PaginationHandler.getPage(chat.log, null, null)
        new ResponseEntity<>(messages, HttpStatus.OK)
    }

    @GetMapping("/sessions/{sessionId}/chats")
    ResponseEntity<List<String>> getSessionChats(@PathVariable String sessionId, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> count) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.SESSION_NOT_FOUND_EXCEPTION)

        List<String> chats = (session.chatLog && session.chatLog.log) ?
                PaginationHandler.getPage(session.chatLog.log, page, count) :
                []
        new ResponseEntity<>(chats, HttpStatus.OK)
    }
}
