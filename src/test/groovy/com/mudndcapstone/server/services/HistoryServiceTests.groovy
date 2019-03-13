package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.repositories.HistoryRepository
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
class HistoryServiceTests {

    @Mock HistoryRepository historyRepository

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

}
