package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.repositories.ChatRepository
import com.mudndcapstone.server.services.ChatService
import org.springframework.beans.factory.annotation.Autowired

class ChatServiceImpl implements ChatService {

    @Autowired ChatRepository chatRepository

    @Override
    List<Chat> getAllChats() {
        chatRepository.findAll().asList()
    }

    @Override
    Chat getChatById(Long id) {
        chatRepository.findById(id).orElse(null)
    }

    @Override
    Chat createChat(Chat chat) {
        chatRepository.save(chat)
    }

    @Override
    void deleteChat(Long id) {
        chatRepository.deleteById(id)
    }
}
