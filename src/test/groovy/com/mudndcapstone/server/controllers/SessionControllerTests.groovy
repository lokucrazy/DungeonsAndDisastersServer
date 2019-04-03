package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.models.dto.ChatDto
import com.mudndcapstone.server.models.dto.HistoryDto
import com.mudndcapstone.server.models.dto.SessionDto
import com.mudndcapstone.server.services.CharacterService
import com.mudndcapstone.server.services.ChatService
import com.mudndcapstone.server.services.HistoryService
import com.mudndcapstone.server.services.SessionService
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import static org.mockito.ArgumentMatchers.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class SessionControllerTests {

    @Mock SessionService sessionService
    @Mock CharacterService characterService
    @Mock ChatService chatService
    @Mock HistoryService historyService

    @InjectMocks
    SessionController sessionController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenSessionList_whenSessionServiceReturnsList_thenSessionControllerReturnsList() {
        // Given
        Set<Session> sessions = [new Session()]
        Set<SessionDto> sessionDtos = sessionService.buildDtoSetFrom(sessions)
        ResponseEntity response

        // When
        Mockito.when(sessionService.getAllSessions()).thenReturn(sessions)
        response = sessionController.getAllSessions()

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == sessionDtos
        Mockito.verify(sessionService, Mockito.atLeastOnce()).getAllSessions()
    }

    @Test
    void givenSession_whenSessionHasCharacters_thenSessionControllerReturnsCharacters() {
        // Given
        Session session = new Session()
        String testUuid = UUID.randomUUID().toString()
        HashSet<Character> characters = [new Character(), new Character()]
        Set<CharacterDto> characterDtos
        ResponseEntity response

        // When
        session.setIdentifier(testUuid)
        session.setCharacters(characters)
        characterDtos = characterService.buildDtoSetFrom(session.characters)
        Mockito.when(sessionService.getSessionById(testUuid)).thenReturn(session)
        response = sessionController.getAllSessionsCharacters(testUuid)

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == characterDtos
        Mockito.verify(sessionService, Mockito.atLeastOnce()).getSessionById(testUuid)
    }

    @Test
    void givenSessionWithNoChats_whenGettingSessionChats_thenSessionControllerReturnsEmptyChatLog() {
        // Given
        Session session = new Session()
        ResponseEntity response

        // When
        Mockito.when(sessionService.getSessionById("test")).thenReturn(session)
        response = sessionController.getSessionChats("test",Optional.empty(),Optional.empty())

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
        response = sessionController.getSessionChats("test",Optional.empty(),Optional.empty())

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
        response = sessionController.createChat("test", "hello world",Optional.empty(),Optional.empty())

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
        response = sessionController.createChat("test", "hello world",Optional.empty(),Optional.empty())

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == ["message 1", "message 2", "hello world"]
        Mockito.verify(sessionService, Mockito.atLeastOnce()).getSessionById("test")
    }

    @Test
    void givenHistoryList_whenHistoryServiceReturnsList_thenHistoryControllerReturnsList() {
        // Given
        Set<History> histories = [new History()]
        Set<HistoryDto> historyDtos = historyService.buildDtoSetFrom(histories)
        ResponseEntity response

        // When
        Mockito.when(historyService.getAllHistories()).thenReturn(histories)
        response = sessionController.getAllHistories()

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == historyDtos
        Mockito.verify(historyService, Mockito.atLeastOnce()).getAllHistories()
    }

}
