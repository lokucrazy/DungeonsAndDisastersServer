package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.services.ChatService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/chats")
class ChatController {

    @Autowired ChatService chatService

    @GetMapping
    ResponseEntity<List<Chat>> getAllChats() {
        List<Chat> allChats = chatService.getAllChats()
        new ResponseEntity<>(allChats, HttpStatus.OK)
    }

}
