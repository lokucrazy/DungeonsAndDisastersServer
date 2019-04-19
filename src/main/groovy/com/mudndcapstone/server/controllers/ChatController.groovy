package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.Messenger
import com.mudndcapstone.server.models.dto.ChatDto
import com.mudndcapstone.server.services.ChatService
import com.mudndcapstone.server.services.SessionService
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

    @GetMapping("/chats")
    ResponseEntity<Set<ChatDto>> getAllChats() {
        Set<Chat> chats = chatService.getAllChats()
        Set<ChatDto> chatDtos = chatService.buildDtoSetFrom(chats)
        new ResponseEntity<>(chatDtos, HttpStatus.OK)
    }

    @PostMapping("/chats")
    ResponseEntity<ChatDto> createChat(@Valid @RequestBody ChatDto chatDto) {
        Chat chatRequest = chatService.buildChatFrom(chatDto)
        Chat chat = chatService.createChat(chatRequest)
        if (!chat) return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)

        ChatDto created = chatService.buildDtoFrom(chat)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

    @GetMapping("/chats/{chatId}")
    ResponseEntity<ChatDto> getChatById(@PathVariable String chatId) {
        Chat chat = chatService.getChatById(chatId)
        if (!chat) return new ResponseEntity(HttpStatus.BAD_REQUEST)

        ChatDto chatDto = chatService.buildDtoFrom(chat)
        new ResponseEntity<>(chatDto, HttpStatus.OK)
    }

    @PutMapping("/{chatId}")
    ResponseEntity<List<String>> addMessage(@PathVariable String chatId, @Valid @RequestBody Messenger messenger) {
        Chat chatRequest = chatService.getChatById(chatId)
        if (!chatRequest) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "chatId not found")

        Chat chat = chatService.addMessage(chatRequest, messenger.message)
        if (!chat) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "message could not be added")

        List<String> messages = PaginationHandler.getPage(chat.log, null, null)
        new ResponseEntity<>(messages, HttpStatus.OK)
    }

    @DeleteMapping("/chats/{chatId}")
    ResponseEntity deleteChat(@PathVariable String chatId) {
        chatService.deleteChat(chatId)
        new ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/sessions/{sessionId}/chats")
    ResponseEntity<List<String>> getSessionChats(@PathVariable String sessionId, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> count) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) return new ResponseEntity<>(HttpStatus.BAD_REQUEST)
        if (!session.chatLog || !session.chatLog.log) return new ResponseEntity<>([], HttpStatus.OK)

        List<String> chats = PaginationHandler.getPage(session.chatLog.log, page, count)
        new ResponseEntity<>(chats, HttpStatus.OK)
    }

    @PostMapping("/sessions/{sessionId}/chats")
    ResponseEntity createChat(@PathVariable String sessionId, @RequestBody String message, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> count) {
        new ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

}
