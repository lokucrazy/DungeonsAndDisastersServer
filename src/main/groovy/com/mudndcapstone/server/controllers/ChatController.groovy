package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.dto.ChatDto
import com.mudndcapstone.server.services.ChatService
import com.mudndcapstone.server.utils.Messenger
import com.mudndcapstone.server.utils.PaginationHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

@RestController
@RequestMapping("/chats")
class ChatController {

    @Autowired ChatService chatService

    @GetMapping
    ResponseEntity<Set<ChatDto>> getAllChats() {
        Set<Chat> chats = chatService.getAllChats()
        Set<ChatDto> chatDtos = chatService.buildDtoSetFrom(chats)
        new ResponseEntity<>(chatDtos, HttpStatus.OK)
    }

    @PostMapping
    ResponseEntity<ChatDto> createChat(@Valid @RequestBody ChatDto chatDto) {
        Chat chatRequest = chatService.buildChatFrom(chatDto)
        Chat chat = chatService.createChat(chatRequest)
        if (!chat) return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)

        ChatDto created = chatService.buildDtoFrom(chat)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

    @GetMapping("/{chatId}")
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

    @DeleteMapping("/{chatId}")
    ResponseEntity deleteChat(@PathVariable String chatId) {
        chatService.deleteChat(chatId)
        new ResponseEntity(HttpStatus.OK)
    }

}
