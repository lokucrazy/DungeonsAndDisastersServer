package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.dto.ChatDto
import com.mudndcapstone.server.services.impl.ChatServiceImpl

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
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid

@RestController
@RequestMapping("/chats")
class ChatController {

    @Autowired ChatServiceImpl chatService

    @GetMapping
    ResponseEntity<List<ChatDto>> getAllChats() {
        List<Chat> chats = chatService.getAllChats()
        List<ChatDto> chatDtos = chatService.buildDtoListFrom(chats)
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
    ResponseEntity<ChatDto> getChatById(@PathVariable Long chatId) {
        Chat chat = chatService.getChatById(chatId)
        if (!chat) return new ResponseEntity(HttpStatus.BAD_REQUEST)

        ChatDto chatDto = chatService.buildDtoFrom(chat)
        new ResponseEntity<>(chatDto, HttpStatus.OK)
    }

    @PutMapping("/{chatId}")
    ResponseEntity<ChatDto> updateChat(@PathVariable Long chatId, @Valid @RequestBody ChatDto chatDto) {
        new ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @DeleteMapping("/{chatId}")
    ResponseEntity deleteChat(@PathVariable Long chatId) {
        chatService.deleteChat(chatId)
        new ResponseEntity(HttpStatus.OK)
    }

}
