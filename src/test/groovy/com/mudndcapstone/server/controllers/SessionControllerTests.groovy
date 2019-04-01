package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.models.dto.ChatDto
import com.mudndcapstone.server.models.dto.HistoryDto
import com.mudndcapstone.server.models.dto.SessionDto
import com.mudndcapstone.server.services.CharacterService
import com.mudndcapstone.server.services.ChatService
import com.mudndcapstone.server.services.HistoryService
import com.mudndcapstone.server.services.MapService
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
    @Mock MapService mapService
    @Mock HistoryService historyService

    @InjectMocks
    SessionController sessionController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenSessionDTOWithNoIdentifier_whenSessionServiceCreate_thenSessionControllerReturnsSession() {
        // Given
        Session session = new Session()
        Chat chat = new Chat()
        Map map = new Map()
        SessionDto sessionDto = new SessionDto()
        sessionDto.dmId = "test"
        ResponseEntity response

        // When
        Mockito.when(sessionService.buildSessionFrom(any(SessionDto))).thenReturn(session)
        Mockito.when(chatService.createChat(any(Chat))).thenReturn(chat)
        Mockito.when(mapService.createMap(any(Map))).thenReturn(map)
        Mockito.when(sessionService.createSession(any(Session))).thenReturn(session)
        Mockito.when(sessionService.buildDtoFrom(any(Session))).thenReturn(sessionDto)
        response = sessionController.createSession(sessionDto)

        //Then
        assert response.statusCode == HttpStatus.CREATED
        assert response.body == sessionDto
        Mockito.verify(chatService, Mockito.atLeastOnce()).createChat(any(Chat))
        Mockito.verify(mapService, Mockito.atLeastOnce()).createMap(any(Map))
        Mockito.verify(sessionService, Mockito.atLeastOnce()).createSession(any(Session))
    }

    @Test
    void givenSessionDTOWithIdentifier_whenSessionServiceCreate_thenSessionControllerReturnsSession() {
        // Given
        Session oldSession = new Session()
        Session newSession = new Session()
        oldSession.identifier = "oldIdentifier"
        newSession.identifier = "newIdentifier"
        History history = oldSession
        newSession.history = history
        SessionDto oldSessionDto = new SessionDto()
        SessionDto newSessionDto = new SessionDto()
        oldSessionDto.dmId = "test"
        oldSessionDto.identifier = "oldIdentifier"
        newSessionDto.dmId = "test"
        newSessionDto.identifier = "newIdentifier"

        ResponseEntity response

        // When
        Mockito.when(sessionService.buildSessionFrom(oldSessionDto)).thenReturn(oldSession)
        Mockito.when(sessionService.createSession(any(Session))).thenReturn(newSession)
        Mockito.when(sessionService.moveRelationships(oldSession.identifier, newSession.identifier)).thenReturn(newSession)
        Mockito.when(historyService.convertSessionToHistory(oldSession.identifier)).thenReturn(history)
        Mockito.when(sessionService.updateSession(newSession)).thenReturn(newSession)
        Mockito.when(sessionService.buildDtoFrom(newSession)).thenReturn(newSessionDto)
        response = sessionController.createSession(oldSessionDto)

        //Then
        assert response.statusCode == HttpStatus.CREATED
        assert response.body == newSessionDto
        assert newSessionDto.identifier == newSession.identifier
        Mockito.verify(sessionService, Mockito.atLeastOnce()).createSession(any(Session))
        Mockito.verify(sessionService, Mockito.atLeastOnce()).moveRelationships(any(String), any(String))
        Mockito.verify(historyService, Mockito.atLeastOnce()).convertSessionToHistory(any(String))
        Mockito.verify(sessionService, Mockito.atLeastOnce()).updateSession(any(Session))
    }

    @Test
    void givenNullSessionDTO_whenSessionServiceCreate_ThenSessionControllerReturnsError() {
        // Given
        SessionDto sessionDto = null
        ResponseEntity response

        // When
        response = sessionController.createSession(sessionDto)

        // Then
        assert response.statusCode == HttpStatus.BAD_REQUEST
    }

    @Test
    void givenSessionDTOWithNoDmId_whenSessionServiceCreate_ThenSessionControllerReturnsError() {
        // Given
        SessionDto sessionDto = new SessionDto()
        ResponseEntity response

        // When
        response = sessionController.createSession(sessionDto)

        // Then
        assert response.statusCode == HttpStatus.BAD_REQUEST
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

    @Ignore("fixed in a different branch")
    @Test
    void givenSessionWithNoChats_whenGettingSessionChats_thenSessionControllerReturnsEmptyChatLog() {
        // Given
        Session session = new Session()
        ResponseEntity response

        // When
        Mockito.when(sessionService.getSessionById("test")).thenReturn(session)
        response = sessionController.getSessionChats("test")

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == []
        Mockito.verify(sessionService, Mockito.atLeastOnce()).getSessionById("test")
    }

    @Ignore("fixed in a different branch")
    @Test
    void givenSessionWithChats_whenGettingSessionChats_thenSessionControllerReturnsChats() {
        // Given
        Session session = new Session()
        Chat chat = new Chat()
        List<String> chatLog = ["message 1", "message 2"]
        ChatDto chatDto
        ResponseEntity response

        // When
        chat.setLog(chatLog)
        session.setChatLog(chat)
        Mockito.when(sessionService.getSessionById("test")).thenReturn(session)
        response = sessionController.getSessionChats("test")
        chatDto = chatService.buildDtoFrom(session.chatLog)

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == chatDto
        Mockito.verify(sessionService, Mockito.atLeastOnce()).getSessionById("test")
    }

    @Ignore("fixed in a different branch")
    @Test
    void givenSessionWithNoChats_whenAddingSessionChats_thenSessionControllerReturnsSessionWithNewChat() {
        // Given
        Session session = new Session()
        ChatDto chatDto
        ResponseEntity response

        // When
        Mockito.when(sessionService.getSessionById("test")).thenReturn(session)
        response = sessionController.createChat("test", "hello world")
        chatDto = chatService.buildDtoFrom(session.chatLog)

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == chatDto
        Mockito.verify(sessionService, Mockito.atLeastOnce()).getSessionById("test")
    }

    @Ignore("broken") // TODO: fix broken test
    @Test
    void givenSessionWithChats_whenAddingSessionChats_thenSessionControllerReturnsSessionWithNewChat() {
        // Given
        Session session = new Session()
        Chat chat = new Chat()
        List<String> chatLog = ["message 1", "message 2"]
        ChatDto chatDto
        ResponseEntity response

        // When
        chat.setLog(chatLog)
        session.setChatLog(chat)
        Mockito.when(sessionService.getSessionById("test")).thenReturn(session)
        response = sessionController.createChat("test", "hello world")
        chatDto = chatService.buildDtoFrom(session.chatLog)

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == chatDto
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
