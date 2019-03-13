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
        CombatDto combatDto = new CombatDto()
        String previousCombatId = UUID.randomUUID().toString()
        String sessionId = UUID.randomUUID().toString()
        Set<String> enemyIds = [UUID.randomUUID().toString()]

        // When
        combatDto.setPreviousCombatId(previousCombatId)
        combatDto.setSessionId(sessionId)
        combatDto.setEnemyIds(enemyIds)

        // Then
        assert !combatDto.identifier
        assert combatDto.previousCombatId == previousCombatId
        assert combatDto.sessionId == sessionId
        assert combatDto.enemyIds == enemyIds
    }

}
