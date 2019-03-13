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
        String dmId = UUID.randomUUID().toString()
        List<String> nonCombatLog = ["nc test"]
        List<String> combatLog = ["c test"]
        Date dateEnded = new Date()
        String historyId = UUID.randomUUID().toString()
        String chatId = UUID.randomUUID().toString()
        String mapId = UUID.randomUUID().toString()
        String combatId = UUID.randomUUID().toString()
        Set<String> npcIds = [UUID.randomUUID().toString()]
        Set<String> playerIds = [UUID.randomUUID().toString()]
        Set<String> characterIds = [UUID.randomUUID().toString()]

        // When
        sessionDto.setDmId(dmId)
        sessionDto.setNonCombatLog(nonCombatLog)
        sessionDto.setCombatLog(combatLog)
        sessionDto.setDateEnded(dateEnded)
        sessionDto.setHistoryId(historyId)
        sessionDto.setChatId(chatId)
        sessionDto.setMapId(mapId)
        sessionDto.setCombatId(combatId)
        sessionDto.setNpcIds(npcIds)
        sessionDto.setPlayerIds(playerIds)
        sessionDto.setCharacterIds(characterIds)

        // Then
        assert !sessionDto.identifier
        assert sessionDto.dmId == dmId
        assert sessionDto.nonCombatLog == nonCombatLog
        assert sessionDto.combatLog == combatLog
        assert sessionDto.dateEnded == dateEnded
        assert sessionDto.historyId == historyId
        assert sessionDto.chatId == chatId
        assert sessionDto.mapId == mapId
        assert sessionDto.combatId == combatId
        assert sessionDto.npcIds == npcIds
        assert sessionDto.playerIds == playerIds
        assert sessionDto.characterIds == characterIds
    }

}
