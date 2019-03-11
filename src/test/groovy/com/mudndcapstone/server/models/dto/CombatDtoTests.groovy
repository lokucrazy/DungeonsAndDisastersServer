package com.mudndcapstone.server.models.dto


import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class CombatDtoTests {

    @Test
    void givenEmptyCombatDto_thenReturnEmptyCombatDtoObject() {
        // Given
        CombatDto combatDto = new CombatDto()

        // Then
        Assert.assertNull(combatDto.identifier)
        Assert.assertNull(combatDto.previousCombatId)
        Assert.assertNull(combatDto.sessionId)
        Assert.assertNull(combatDto.enemyIds)
    }

    @Test
    void givenCombatDto_whenAddProperties_thenCombatDtoObjectHasProperties() {
        // Given
        String testUuid = UUID.randomUUID().toString()
        CombatDto combatDto = new CombatDto()
        List<String> enemyIds = [testUuid]

        // When
        combatDto.setPreviousCombatId(testUuid)
        combatDto.setSessionId(testUuid)
        combatDto.setEnemyIds(enemyIds)

        // Then
        Assert.assertNull(combatDto.identifier)
        Assert.assertEquals(combatDto.previousCombatId, testUuid)
        Assert.assertEquals(combatDto.sessionId, testUuid)
        Assert.assertEquals(combatDto.enemyIds, enemyIds)
    }

}
