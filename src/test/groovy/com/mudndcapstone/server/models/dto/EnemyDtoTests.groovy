package com.mudndcapstone.server.models.dto

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class EnemyDtoTests {

    @Test
    void givenEmptyEnemyDto_thenReturnEmptyEnemyDtoObject() {
        // Given
        EnemyDto enemyDto = new EnemyDto()

        // Then
        assert !enemyDto.identifier
        assert !enemyDto.combatId
    }

    @Test
    void givenEnemyDto_whenAddProperties_thenEnemyDtoObjectHasProperties() {
        // Given
        EnemyDto enemyDto = new EnemyDto()

        // When
        enemyDto.setCombatId(500)

        // Then
        assert !enemyDto.identifier
        assert enemyDto.combatId == 500
    }

}
