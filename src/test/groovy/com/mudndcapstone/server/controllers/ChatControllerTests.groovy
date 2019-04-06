package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.dto.ChatDto
import com.mudndcapstone.server.services.ChatService
import com.mudndcapstone.server.services.SessionService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner

import static org.mockito.ArgumentMatchers.notNull
import static org.mockito.ArgumentMatchers.notNull

@RunWith(SpringRunner)
@SpringBootTest
class ChatControllerTests {

    @Mock ChatService chatService
    @Mock SessionService sessionService

    @InjectMocks
    ChatController chatController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenChatList_whenChatServiceReturnsList_thenChatControllerReturnsList() {
        // Given
        Set<Chat> chats = [new Chat()]
        Set<ChatDto> chatDtos = chatService.buildDtoSetFrom(chats)
        ResponseEntity response

        // When
        Mockito.when(chatService.getAllChats()).thenReturn(chats)
        response = chatController.getAllChats()

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == chatDtos
        Mockito.verify(chatService, Mockito.atLeastOnce()).getAllChats()
    }

    @Test
    void givenSessionWithNoChats_whenGettingSessionChats_thenSessionControllerReturnsEmptyChatLog() {
        // Given
        Session session = new Session()
        ResponseEntity response

        // When
        Mockito.when(sessionService.getSessionById("test")).thenReturn(session)
        response = chatController.getSessionChats("test", Optional.empty(), Optional.empty())

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == []
        Mockito.verify(sessionService, Mockito.atLeastOnce()).getSessionById("test")
    }

    @Test
    void givenSessionWithChats_whenGettingSessionChats_thenSessionControllerReturnsChats() {
        // Given
        Session session = new Session()
        Chat chat = new Chat()
        List<String> chatLog = ["message 1", "message 2"]
        ResponseEntity response

        // When
        chat.setLog(chatLog)
        session.setChatLog(chat)
        Mockito.when(sessionService.getSessionById("test")).thenReturn(session)
        response = chatController.getSessionChats("test", Optional.empty(), Optional.empty())

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == chatLog
        Mockito.verify(sessionService, Mockito.atLeastOnce()).getSessionById("test")
    }

    @Test
    void givenSessionWithNoChats_whenAddingSessionChats_thenSessionControllerReturnsSessionWithNewChat() {
        // Given
        Session session = new Session()
        ResponseEntity response

        // When
        Mockito.when(sessionService.getSessionById("test")).thenReturn(session)
        Mockito.when(chatService.createChat((ChatDto)notNull())).thenReturn(new Chat(log: ["hello world"]))
        response = chatController.createChat("test", "hello world", Optional.empty(), Optional.empty())

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == ["hello world"]
        Mockito.verify(sessionService, Mockito.atLeastOnce()).getSessionById("test")
    }

    @Test
    void givenSessionWithChats_whenAddingSessionChats_thenSessionControllerReturnsSessionWithNewChat() {
        // Given
        Session session = new Session()
        Chat chat = new Chat()
        List<String> chatLog = ["message 1", "message 2"]
        ResponseEntity response

        // When
        chat.setLog(chatLog)
        session.setChatLog(chat)
        Mockito.when(chatService.buildDtoFrom(session.chatLog)).thenReturn(new ChatDto(sessionId: "test", log: chatLog))
        Mockito.when(chatService.createChat((ChatDto)notNull())).thenReturn(new Chat(log: ["message 1", "message 2", "hello world"]))
        Mockito.when(sessionService.getSessionById("test")).thenReturn(session)
        response = chatController.createChat("test", "hello world", Optional.empty(), Optional.empty())

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == ["message 1", "message 2", "hello world"]
        Mockito.verify(sessionService, Mockito.atLeastOnce()).getSessionById("test")
    }

}
