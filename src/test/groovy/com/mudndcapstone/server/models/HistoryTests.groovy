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
    void givenEmptyHistory_thenReturnAlmostEmptyHistoryObject() {
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
        List<String> nonCombatLog = new ArrayList<>()
        List<String> combatLog = new ArrayList<>()
        History previousHistory = new History()

        // When
        history.setNonCombatLog(nonCombatLog)
        history.setCombatLog(combatLog)
        history.setHistory(previousHistory)

        // Then
        Assert.assertNull(history.id)
        Assert.assertEquals(history.nonCombatLog, nonCombatLog)
        Assert.assertEquals(history.combatLog, combatLog)
        Assert.assertEquals(history.history, previousHistory)
    }

}
