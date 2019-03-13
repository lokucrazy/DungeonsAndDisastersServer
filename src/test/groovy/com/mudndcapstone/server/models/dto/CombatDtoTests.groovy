package com.mudndcapstone.server.models.dto

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
        assert !combatDto.identifier
        assert !combatDto.previousCombatId
        assert !combatDto.sessionId
        assert !combatDto.enemyIds
    }

    @Test
    void givenCombatDto_whenAddProperties_thenCombatDtoObjectHasProperties() {
        // Given
        String testUuid = UUID.randomUUID().toString()
        CombatDto combatDto = new CombatDto()
        Set<String> enemyIds = [testUuid]

        // When
        combatDto.setPreviousCombatId(testUuid)
        combatDto.setSessionId(testUuid)
        combatDto.setEnemyIds(enemyIds)

        // Then
        assert !combatDto.identifier
        assert combatDto.previousCombatId == testUuid
        assert combatDto.sessionId == testUuid
        assert combatDto.enemyIds == enemyIds
    }

}
