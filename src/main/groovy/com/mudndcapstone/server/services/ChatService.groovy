package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Chat

interface ChatService {
    List<Chat> getAllChats()
    Chat getChatById(Long id)
    Chat createChat(Chat chat)
    void deleteChat(Long id)
}