package com.mudndcapstone.server.models

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class EnemyTests {

    @Test
    void givenEmptyEnemy_thenReturnEmptyEnemyObject() {
        // Given
        Enemy enemy = new Enemy()

        // Then
        Assert.assertNull(enemy.id)
        Assert.assertNull(enemy.combat)
    }

    @Test
    void givenEnemy_whenAddProperties_thenEnemyObjectHasProperties() {
        // Given
        Enemy enemy = new Enemy()
        Combat combat = new Combat()

        // When
        enemy.setCombat(combat)

        // Then
        Assert.assertNull(enemy.id)
        Assert.assertEquals(enemy.combat, combat)
    }

}
