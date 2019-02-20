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

    @Autowired
    private HistoryRepository historyRepository

    @Test
    void whenFindById_returnHistory() {
        //Given
        History history = new History()
        historyRepository.save(history)
        //When
        Optional<History> found = historyRepository.findById(history.id)
        //Then
        Assert.assertEquals(found.get().id, history.id)
    }
}
