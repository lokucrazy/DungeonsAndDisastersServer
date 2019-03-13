package com.mudndcapstone.server.models.dto

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class SessionDtoTests {

    @Test
    void givenEmptySessionDto_thenReturnEmptySessionDtoObject() {
        // Given
        SessionDto sessionDto = new SessionDto()

        // Then
        assert !sessionDto.identifier
        assert !sessionDto.dmId
        assert !sessionDto.nonCombatLog
        assert !sessionDto.combatLog
        assert !sessionDto.dateEnded
        assert !sessionDto.historyId
        assert !sessionDto.chatId
        assert !sessionDto.mapId
        assert !sessionDto.combatId
        assert !sessionDto.npcIds
        assert !sessionDto.playerIds
        assert !sessionDto.characterIds
    }

    @Test
    void givenSessionDto_whenAddProperties_thenSessionDtoObjectHasProperties() {
        // Given
        SessionDto sessionDto = new SessionDto()
        List<String> nonCombatLog = ["nc test"]
        List<String> combatLog = ["c test"]
        Date dateEnded = new Date()
        Set<Long> npcIds = [1100]
        Set<Long> playerIds = [1300]
        Set<Long> characterIds = [1500]

        // When
        sessionDto.setDmId(100)
        sessionDto.setNonCombatLog(nonCombatLog)
        sessionDto.setCombatLog(combatLog)
        sessionDto.setDateEnded(dateEnded)
        sessionDto.setHistoryId(300)
        sessionDto.setChatId(500)
        sessionDto.setMapId(700)
        sessionDto.setCombatId(900)
        sessionDto.setNpcIds(npcIds)
        sessionDto.setPlayerIds(playerIds)
        sessionDto.setCharacterIds(characterIds)

        // Then
        assert !sessionDto.identifier
        assert sessionDto.dmId == 100
        assert sessionDto.nonCombatLog == nonCombatLog
        assert sessionDto.combatLog == combatLog
        assert sessionDto.dateEnded == dateEnded
        assert sessionDto.historyId == 300
        assert sessionDto.chatId == 500
        assert sessionDto.mapId == 700
        assert sessionDto.combatId == 900
        assert sessionDto.npcIds == npcIds
        assert sessionDto.playerIds == playerIds
        assert sessionDto.characterIds == characterIds
    }

}
