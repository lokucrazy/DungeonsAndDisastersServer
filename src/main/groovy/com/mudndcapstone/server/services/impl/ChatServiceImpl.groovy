package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.dto.ChatDto
import com.mudndcapstone.server.repositories.ChatRepository
import com.mudndcapstone.server.services.ChatService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class ChatServiceImpl {

    @Autowired ChatRepository chatRepository
    @Autowired ModelMapper modelMapper

    Set<Chat> getAllChats() {
        chatRepository.findAll().toSet()
    }

    Chat getChatById(Long id) {
        chatRepository.findById(id).orElse(null)
    }

    Chat createChat(Chat chat) {
        chatRepository.save(chat)
    }

    void deleteChat(Long id) {
        chatRepository.deleteById(id)
    }

    Chat buildChatFrom(ChatDto chatDto) {
        Chat chat = modelMapper.map(chatDto, Chat)

        chat
    }

    ChatDto buildDtoFrom(Chat chat) {
        ChatDto chatDto = modelMapper.map(chat, ChatDto)

        Long sessionId = chat.session ? chat.session.identifier : null

        chatDto.setSessionId(sessionId)

        chatDto
    }

    Set<ChatDto> buildDtoSetFrom(Set<Chat> chats) {
        chats.stream().map({ chat -> buildDtoFrom(chat) }).collect(Collectors.toSet())
    }

}
