package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Enemy
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class EnemyRepositoryTests {

    @Autowired EnemyRepository enemyRepository

    @Test
    void givenEnemy_whenEnemySavedToRepository_thenEnemyReturned() {
        // Given
        Enemy enemy = new Enemy()
        Enemy found

        // When
        enemyRepository.save(enemy)
        found = enemyRepository.findById(enemy.identifier).orElse(null)

        // Then
        Assert.assertNotNull(found)
        Assert.assertEquals(found.identifier, enemy.identifier)
    }

}
