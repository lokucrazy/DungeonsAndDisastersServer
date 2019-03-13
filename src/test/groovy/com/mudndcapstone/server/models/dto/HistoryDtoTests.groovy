package com.mudndcapstone.server.models.dto

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
        assert !historyDto.identifier
        assert !historyDto.nonCombatLog
        assert !historyDto.combatLog
        assert !historyDto.dateEnded
        assert !historyDto.historyId
    }

    @Test
    void givenHistoryDto_whenAddProperties_thenHistoryDtoObjectHasProperties() {
        // Given
        String testUuid = UUID.randomUUID().toString()
        HistoryDto historyDto = new HistoryDto()
        List<String> nonCombatLog = ["nc test"]
        List<String> combatLog = ["c test"]
        Date dateEnded = new Date()

        // When
        historyDto.setNonCombatLog(nonCombatLog)
        historyDto.setCombatLog(combatLog)
        historyDto.setDateEnded(dateEnded)
        historyDto.setHistoryId(testUuid)

        // Then
        assert !historyDto.identifier
        assert historyDto.nonCombatLog == nonCombatLog
        assert historyDto.combatLog == combatLog
        assert historyDto.dateEnded == dateEnded
        assert historyDto.historyId == testUuid
    }

}
