package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.dto.ChatDto
import com.mudndcapstone.server.repositories.ChatRepository
import com.mudndcapstone.server.utils.Auditor
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class ChatService {

    @Autowired ChatRepository chatRepository
    @Autowired SessionService sessionService
    @Autowired ModelMapper modelMapper

    Set<Chat> getAllChats() {
        chatRepository.findAll().toSet()
    }

    Chat getChatById(String id) {
        chatRepository.findById(id).orElse(null)
    }

    Chat createChat(Chat chat) {
        if (!chat.session) return null

        Auditor.enableAuditing(chat)
        chatRepository.save(chat)
    }

    Chat addMessage(Chat chat, String message) {
        if (!chat) return null
        if (!message) return chat

        chat.log == null ? chat.log = [message] : chat.log << message
        chatRepository.save(chat)
    }

    void deleteChat(String id) {
        chatRepository.deleteById(id)
    }

    Chat buildChatFrom(ChatDto chatDto, Session session) {
        Chat chat = modelMapper.map(chatDto, Chat)

        chat.session = session

        chat
    }

    ChatDto buildDtoFrom(Chat chat) {
        ChatDto chatDto = modelMapper.map(chat, ChatDto)

        String sessionId = chat.session ? chat.session.identifier : null
        chatDto.setSessionId(sessionId)

        chatDto
    }

    Set<ChatDto> buildDtoSetFrom(Set<Chat> chats) {
        if (!chats) return []
        chats.stream().map({ chat -> buildDtoFrom(chat) }).collect(Collectors.toSet())
    }

}
