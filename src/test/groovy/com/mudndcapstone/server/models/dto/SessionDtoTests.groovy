package com.mudndcapstone.server.models.dto


import org.junit.Assert
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
        Assert.assertNull(sessionDto.identifier)
        Assert.assertNull(sessionDto.dmId)
        Assert.assertNull(sessionDto.nonCombatLog)
        Assert.assertNull(sessionDto.combatLog)
        Assert.assertNull(sessionDto.dateEnded)
        Assert.assertNull(sessionDto.historyId)
        Assert.assertNull(sessionDto.chatId)
        Assert.assertNull(sessionDto.mapId)
        Assert.assertNull(sessionDto.combatId)
        Assert.assertNull(sessionDto.npcIds)
        Assert.assertNull(sessionDto.playerIds)
        Assert.assertNull(sessionDto.characterIds)
    }

    @Test
    void givenSessionDto_whenAddProperties_thenSessionDtoObjectHasProperties() {
        // Given
        String testUuid = UUID.randomUUID().toString()
        SessionDto sessionDto = new SessionDto()
        List<String> nonCombatLog = ["nc test"]
        List<String> combatLog = ["c test"]
        Date dateEnded = new Date()
        List<String> npcIds = [testUuid]
        List<String> playerIds = [testUuid]
        List<String> characterIds = [testUuid]

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
        Assert.assertNull(sessionDto.identifier)
        Assert.assertEquals(sessionDto.dmId, testUuid)
        Assert.assertEquals(sessionDto.nonCombatLog, nonCombatLog)
        Assert.assertEquals(sessionDto.combatLog, combatLog)
        Assert.assertEquals(sessionDto.dateEnded, dateEnded)
        Assert.assertEquals(sessionDto.historyId, testUuid)
        Assert.assertEquals(sessionDto.chatId, testUuid)
        Assert.assertEquals(sessionDto.mapId, testUuid)
        Assert.assertEquals(sessionDto.combatId, testUuid)
        Assert.assertEquals(sessionDto.npcIds, npcIds)
        Assert.assertEquals(sessionDto.playerIds, playerIds)
        Assert.assertEquals(sessionDto.characterIds, characterIds)
    }

}
