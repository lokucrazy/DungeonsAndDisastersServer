package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.services.HistoryService
import com.mudndcapstone.server.services.SessionService
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

    @Mock
    SessionService sessionService

    @Mock
    HistoryService historyService

    @InjectMocks
    SessionController sessionController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenGetAllSessions_returnSessionList() {
        //Given
        List<Session> sessions = new ArrayList<Session>()
        Mockito.when(sessionService.getAllSessions()).thenReturn(sessions.asList())
        //Then
        ResponseEntity response = sessionController.getAllSessions()
        Assert.assertEquals(response.statusCode, HttpStatus.OK)
        Assert.assertEquals(response.body, sessions)
        Mockito.verify(sessionService, Mockito.atLeastOnce()).getAllSessions()
    }
}