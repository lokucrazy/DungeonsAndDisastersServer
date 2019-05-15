package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.repositories.EnemyRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class EnemyServiceTests {

    @Mock EnemyRepository enemyRepository

    @InjectMocks
    EnemyService enemyService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenEnemy_whenEnemyRepositorySavesEnemy_thenEnemyServiceReturnsEnemy() {
        // Given
        Enemy enemy = new Enemy()
        Enemy found

        // When
        enemyRepository.save(enemy)
        Mockito.when(enemyRepository.findById(enemy.identifier)).thenReturn(Optional.of(enemy))
        found = enemyService.getEnemyById(enemy.identifier)

        // Then
        assert enemy == found
    }

}
