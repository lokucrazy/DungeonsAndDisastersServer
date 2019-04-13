package com.mudndcapstone.server.controllers

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.dto.ChatDto
import com.mudndcapstone.server.services.ChatService
import com.mudndcapstone.server.utils.Messenger
import com.mudndcapstone.server.utils.PaginationHandler
import org.apache.tomcat.util.json.JSONParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
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
    ResponseEntity<List<String>> addMessage(@PathVariable String chatId, @Valid @RequestBody Messenger messenger, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> count) {
        if (!chatId) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "chatId not found")
        Chat chatRequest = chatService.getChatById(chatId)
        Chat chat

        chat = chatService.addMessage(chatRequest, messenger.message)

        if (!chat) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "message could not be added")
        List<String> messages = PaginationHandler.getPage(chat.log, page, count)

        new ResponseEntity<>(messages, HttpStatus.OK)
    }

    @DeleteMapping("/{chatId}")
    ResponseEntity deleteChat(@PathVariable String chatId) {
        chatService.deleteChat(chatId)
        new ResponseEntity(HttpStatus.OK)
    }

}
