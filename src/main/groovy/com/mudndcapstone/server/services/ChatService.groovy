package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.request.ChatRequest

interface ChatService {
    List<Chat> getAllChats()
    Chat getChatById(Long id)
    Chat createChat(ChatRequest chatRequestat)
    void deleteChat(Long id)
}