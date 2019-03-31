package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.models.Session
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class SessionRepositoryTests {

    @Autowired SessionRepository sessionRepository
    @Autowired ChatRepository chatRepository
    @Autowired MapRepository mapRepository

    @Test
    void givenSession_whenSessionSavedToRepository_thenSessionReturned() {
        // Given
        Session session = new Session()
        Session found

        // When
        sessionRepository.save(session)
        found = sessionRepository.findById(session.identifier).orElse(null)

        // Then
        assert found
        assert session == found
    }

    @Test
    void givenOldSessionAndNewSession_whenSessionRefactorRelationships_ThenNewSessionReturned() {
        // Given
        Session oldSession = new Session()
        Session newSession = new Session()
        oldSession.chatLog = new Chat()
        oldSession.mapList = new Map()
        Session newSessionPostMove

        // When
        chatRepository.save(oldSession.chatLog)
        mapRepository.save(oldSession.mapList)
        sessionRepository.save(oldSession)
        sessionRepository.save(newSession)

        newSessionPostMove = sessionRepository.refactorRelationships(oldSession.identifier, newSession.identifier).orElse(null)

        // Then
        assert newSession.identifier == newSessionPostMove.identifier
        assert newSessionPostMove.chatLog != null
        assert newSessionPostMove.mapList != null
    }

    @Test
    void givenOldSessionAndNullSession_whenSessionRefactorRelationships_thenNullReturned() {
        // Given
        Session oldSession = new Session()
        Session newSessionPostMove

        // When
        sessionRepository.save(oldSession)

        newSessionPostMove = sessionRepository.refactorRelationships(oldSession.identifier, null).orElse(null)

        // Then
        assert newSessionPostMove == null
    }

    @Test
    void givenNullSessions_whenSessionRefactorRelationships_thenNullReturned() {
        // Given
        Session newSessionPostMove

        // When
        newSessionPostMove = sessionRepository.refactorRelationships(null,null).orElse(null)

        // Then
        assert newSessionPostMove == null
    }

    @Test
    void givenBadSession_whenSessionRefactorRelationships_thenNullReturned() {
        // Given
        String badIdentifier = "bad"
        Session newSession = new Session()
        Session newSessionPostMove

        // When
        sessionRepository.save(newSession)

        newSessionPostMove = sessionRepository.refactorRelationships(badIdentifier, newSession.identifier).orElse(null)

        // Then
        assert newSessionPostMove == null
    }

}
