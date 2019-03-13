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
        Set<Long> enemyIds = [500]

        // When
        combatDto.setPreviousCombatId(100)
        combatDto.setSessionId(300)
        combatDto.setEnemyIds(enemyIds)

        // Then
        assert !combatDto.identifier
        assert combatDto.previousCombatId == 100
        assert combatDto.sessionId == 300
        assert combatDto.enemyIds == enemyIds
    }

}
