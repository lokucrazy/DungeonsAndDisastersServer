package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.models.dto.HistoryDto
import com.mudndcapstone.server.models.dto.SessionDto
import com.mudndcapstone.server.services.impl.CharacterServiceImpl
import com.mudndcapstone.server.services.impl.HistoryServiceImpl
import com.mudndcapstone.server.services.impl.SessionServiceImpl
import org.junit.Assert
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

@RunWith(SpringRunner)
@SpringBootTest
class SessionControllerTests {

    @Mock SessionServiceImpl sessionService
    @Mock CharacterServiceImpl characterService
    @Mock HistoryServiceImpl historyService

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

        // When
        Mockito.when(sessionService.getAllSessions()).thenReturn(sessions)

        // Then
        ResponseEntity response = sessionController.getAllSessions()
        Assert.assertEquals(response.statusCode, HttpStatus.OK)
        Assert.assertEquals(response.body, sessionDtos)
        Mockito.verify(sessionService, Mockito.atLeastOnce()).getAllSessions()
    }

    @Test
    void givenSession_whenSessionHasCharacters_thenSessionControllerReturnsCharacters() {
        // Given
        Session session = new Session()
        HashSet<Character> characters = [new Character(), new Character()]
        Set<CharacterDto> characterDtos

        // When
        session.setIdentifier(1000)
        session.setCharacters(characters)
        characterDtos = characterService.buildDtoSetFrom(session.characters)
        Mockito.when(sessionService.getSessionById(1000)).thenReturn(session)

        // Then
        ResponseEntity response = sessionController.getAllSessionsCharacters(1000)
        Assert.assertEquals(response.statusCode, HttpStatus.OK)
        Assert.assertEquals(response.body, characterDtos)
        Mockito.verify(sessionService, Mockito.atLeastOnce()).getSessionById(1000)
    }

    @Test
    void givenHistoryList_whenHistoryServiceReturnsList_thenHistoryControllerReturnsList() {
        // Given
        Set<History> histories = [new History()]
        Set<HistoryDto> historyDtos = historyService.buildDtoSetFrom(histories)

        // When
        Mockito.when(historyService.getAllHistories()).thenReturn(histories)

        // Then
        ResponseEntity response = sessionController.getAllHistories()
        Assert.assertEquals(response.statusCode, HttpStatus.OK)
        Assert.assertEquals(response.body, historyDtos)
        Mockito.verify(historyService, Mockito.atLeastOnce()).getAllHistories()
    }

}
