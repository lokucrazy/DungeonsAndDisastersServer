package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.History
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class HistoryRepositoryTests {

    @Autowired HistoryRepository historyRepository

    @Test
    void givenHistory_whenHistorySavedToRepository_thenHistoryReturned() {
        // Given
        History history = new History()
        History found

        // When
        historyRepository.save(history)
        found = historyRepository.findById(history.identifier).orElse(null)

        // Then
        Assert.assertNotNull(found)
        Assert.assertEquals(found.identifier, history.identifier)
    }

}
