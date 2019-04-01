package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.repositories.SessionRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class SessionServiceTests {

    @Mock SessionRepository sessionRepository

    @InjectMocks
    SessionService sessionService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenSession_whenSessionRepositorySavesSession_thenSessionServiceReturnsSession() {
        // Given
        Session session = new Session()
        Session found

        // When
        sessionRepository.save(session)
        Mockito.when(sessionRepository.findById(session.identifier)).thenReturn(Optional.of(session))
        found = sessionService.getSessionById(session.identifier)

        // Then
        assert session == found
    }

    @Test
    void givenOldSessionAndNewSession_WhenSessionMoveRelationships_thenReturnsNewSession() {
        // Given
        Session oldSession = new Session()
        Session newSession = new Session()
        oldSession.identifier = "oldIdentifier"
        newSession.identifier = "newIdentifier"
        Session sessionPostMove

        // When
        sessionRepository.save(oldSession)
        sessionRepository.save(newSession)
        Mockito.when(sessionRepository.refactorRelationships(oldSession.identifier, newSession.identifier))
                .thenReturn(Optional.of(newSession))
        sessionPostMove = sessionService.moveRelationships(oldSession.identifier, newSession.identifier)

        // Then
        assert sessionPostMove == newSession
    }

    @Test
    void givenExistingSession_whenSessionUpdateSession_thenReturnsUpdatedSession() {
        // Given
        Session session = new Session()
        session.identifier = "identifier"
        session.dm = new User()
        Session updated

        // When
        sessionRepository.save(session)
        Mockito.when(sessionRepository.existsById(session.identifier)).thenReturn(true)
        Mockito.when(sessionRepository.save(session)).thenReturn(session)
        updated = sessionService.updateSession(session)

        // Then
        assert updated != null
    }

}
