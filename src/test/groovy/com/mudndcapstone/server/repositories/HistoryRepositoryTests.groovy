package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.models.Session
import org.junit.Test
import org.junit.runner.RunWith
import org.neo4j.ogm.model.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class HistoryRepositoryTests {

    @Autowired HistoryRepository historyRepository
    @Autowired SessionRepository sessionRepository

    @Test
    void givenHistory_whenHistorySavedToRepository_thenHistoryReturned() {
        // Given
        History history = new History()
        History found

        // When
        historyRepository.save(history)
        found = historyRepository.findById(history.identifier).orElse(null)

        // Then
        assert found
        assert history == found
    }

    @Test
    void givenSession_whenRemoveSessionLabel_thenHistoryReturned() {
        // Given
        Session session = new Session()
        History history

        // When
        sessionRepository.save(session)

        history = historyRepository.removeSessionLabel(session.identifier).orElse(null)

        // Then
        assert history != null
        assert history.identifier == session.identifier
    }

    @Test
    void givenNullSession_whenRemoveSessionLabel_thenNullReturned() {
        // Given
        History history

        // When
        history = historyRepository.removeSessionLabel(null).orElse(null)

        // Then
        assert history == null
    }

}
