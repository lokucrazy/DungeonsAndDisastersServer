package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.repositories.HistoryRepository
import com.mudndcapstone.server.repositories.SessionRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.internal.matchers.*
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

import javax.validation.constraints.NotNull

@RunWith(SpringRunner)
@DataNeo4jTest
class HistoryServiceTests {

    @Mock HistoryRepository historyRepository
    @Mock SessionRepository sessionRepository

    @InjectMocks
    HistoryService historyService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenHistory_whenHistoryRepositorySavesHistory_thenHistoryServiceReturnsHistory() {
        // Given
        History history = new History()
        History found

        // When
        historyRepository.save(history)
        Mockito.when(historyRepository.findById(history.identifier)).thenReturn(Optional.of(history))
        found = historyService.getHistoryById(history.identifier)

        // Then
        assert history == found
    }

    @Test
    void givenSession_whenConvertSessionToHistory_thenHistoryReturned() {
        // Given
        Session session = new Session()
        session.identifier = "identifier"
        History history = session

        // When
        Mockito.when(historyRepository.removeSessionLabel(session.identifier)).thenReturn(Optional.of(history))

        // Then
        assert history != null
        assert history.identifier == session.identifier
    }

    @Test
    void givenNull_whenConvertSessionToHistory_thenHistoryReturned() {
        // Given
        String id = null
        History history

        // When
        Mockito.when(historyRepository.removeSessionLabel(id)).thenReturn(null)

        // Then
        assert history == null
    }

}
