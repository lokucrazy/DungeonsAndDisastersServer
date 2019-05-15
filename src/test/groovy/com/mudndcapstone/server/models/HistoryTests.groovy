package com.mudndcapstone.server.models

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class HistoryTests {

    @Test
    void givenEmptyHistory_thenReturnEmptyHistoryObject() {
        // Given
        History history = new History()

        // Then
        assert !history.identifier
        assert !history.nonCombatLog
        assert !history.combatLog
        assert !history.history
    }

    @Test
    void givenHistory_whenAddProperties_thenHistoryObjectHasProperties() {
        // Given
        History history = new History()
        List<String> nonCombatLog = ["nc test"]
        List<String> combatLog = ["c test"]
        History previousHistory = new History()
        Date dateEnded = new Date()

        // When
        history.setNonCombatLog(nonCombatLog)
        history.setCombatLog(combatLog)
        history.setHistory(previousHistory)
        history.setDateEnded(dateEnded)

        // Then
        assert !history.identifier
        assert history.nonCombatLog == nonCombatLog
        assert history.combatLog == combatLog
        assert history.history == previousHistory
        assert history.dateEnded == dateEnded
    }

}
