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

}
