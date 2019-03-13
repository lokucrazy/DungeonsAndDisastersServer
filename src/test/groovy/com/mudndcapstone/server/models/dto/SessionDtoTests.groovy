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
        String testUuid = UUID.randomUUID().toString()
        SessionDto sessionDto = new SessionDto()
        List<String> nonCombatLog = ["nc test"]
        List<String> combatLog = ["c test"]
        Date dateEnded = new Date()
        Set<String> npcIds = [testUuid]
        Set<String> playerIds = [testUuid]
        Set<String> characterIds = [testUuid]

        // When
        sessionDto.setDmId(testUuid)
        sessionDto.setNonCombatLog(nonCombatLog)
        sessionDto.setCombatLog(combatLog)
        sessionDto.setDateEnded(dateEnded)
        sessionDto.setHistoryId(testUuid)
        sessionDto.setChatId(testUuid)
        sessionDto.setMapId(testUuid)
        sessionDto.setCombatId(testUuid)
        sessionDto.setNpcIds(npcIds)
        sessionDto.setPlayerIds(playerIds)
        sessionDto.setCharacterIds(characterIds)

        // Then
        assert !sessionDto.identifier
        assert sessionDto.dmId == testUuid
        assert sessionDto.nonCombatLog == nonCombatLog
        assert sessionDto.combatLog == combatLog
        assert sessionDto.dateEnded == dateEnded
        assert sessionDto.historyId == testUuid
        assert sessionDto.chatId == testUuid
        assert sessionDto.mapId == testUuid
        assert sessionDto.combatId == testUuid
        assert sessionDto.npcIds == npcIds
        assert sessionDto.playerIds == playerIds
        assert sessionDto.characterIds == characterIds
    }

}
