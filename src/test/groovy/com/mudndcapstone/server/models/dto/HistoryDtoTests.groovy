package com.mudndcapstone.server.models.dto

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class HistoryDtoTests {

    @Test
    void givenEmptyHistoryDto_thenReturnEmptyHistoryDtoObject() {
        // Given
        HistoryDto historyDto = new HistoryDto()

        // Then
        Assert.assertNull(historyDto.identifier)
        Assert.assertNull(historyDto.nonCombatLog)
        Assert.assertNull(historyDto.combatLog)
        Assert.assertNull(historyDto.dateEnded)
        Assert.assertNull(historyDto.historyId)
    }

    @Test
    void givenHistoryDto_whenAddProperties_thenHistoryDtoObjectHasProperties() {
        // Given
        HistoryDto historyDto = new HistoryDto()
        List<String> nonCombatLog = ["nc test"]
        List<String> combatLog = ["c test"]
        Date dateEnded = new Date()

        // When
        historyDto.setNonCombatLog(nonCombatLog)
        historyDto.setCombatLog(combatLog)
        historyDto.setDateEnded(dateEnded)
        historyDto.setHistoryId(500)

        // Then
        Assert.assertNull(historyDto.identifier)
        Assert.assertEquals(historyDto.nonCombatLog, nonCombatLog)
        Assert.assertEquals(historyDto.combatLog, combatLog)
        Assert.assertEquals(historyDto.dateEnded, dateEnded)
        Assert.assertEquals(historyDto.historyId, 500)
    }

}
