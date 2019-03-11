package com.mudndcapstone.server.models.dto


import org.junit.Assert
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
        Assert.assertNull(enemyDto.identifier)
        Assert.assertNull(enemyDto.combatId)
    }

    @Test
    void givenEnemyDto_whenAddProperties_thenEnemyDtoObjectHasProperties() {
        // Given
        String testUuid = UUID.randomUUID().toString()
        EnemyDto enemyDto = new EnemyDto()

        // When
        enemyDto.setCombatId(testUuid)

        // Then
        Assert.assertNull(enemyDto.identifier)
        Assert.assertEquals(enemyDto.combatId, testUuid)
    }

}
