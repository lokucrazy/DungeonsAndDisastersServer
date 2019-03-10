package com.mudndcapstone.server.models

import org.junit.Assert
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
        Assert.assertNull(history.id)
        Assert.assertNull(history.nonCombatLog)
        Assert.assertNull(history.combatLog)
        Assert.assertNull(history.history)
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
        Assert.assertNull(history.id)
        Assert.assertEquals(history.nonCombatLog, nonCombatLog)
        Assert.assertEquals(history.combatLog, combatLog)
        Assert.assertEquals(history.history, previousHistory)
        Assert.assertEquals(history.dateEnded, dateEnded)
    }

}
