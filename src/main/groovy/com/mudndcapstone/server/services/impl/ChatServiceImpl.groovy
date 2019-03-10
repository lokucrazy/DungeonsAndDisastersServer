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
class ChatServiceImpl implements ChatService {

    @Autowired ChatRepository chatRepository
    @Autowired ModelMapper modelMapper

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

    List<ChatDto> buildDtoListFrom(List<Chat> chats) {
        chats.stream().map({ chat -> buildDtoFrom(chat) }).collect(Collectors.toList())
    }

}
