package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.repositories.HistoryRepository
import org.junit.Assert
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
class HistoryServiceImplTests {

    @Mock HistoryRepository historyRepository

    @InjectMocks
    HistoryServiceImpl historyService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenHistory_whenHistoryRepositorySavesHistory_thenHistoryServiceReturnsHistory() {
        // Given
        History history = new History()

        // When
        historyRepository.save(history)
        Mockito.when(historyRepository.findById(history.identifier)).thenReturn(Optional.of(history))

        // Then
        Assert.assertEquals(historyService.getHistoryById(history.identifier), history)
    }

}
