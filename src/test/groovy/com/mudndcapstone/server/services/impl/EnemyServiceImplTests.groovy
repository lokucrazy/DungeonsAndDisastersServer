package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.repositories.EnemyRepository
import org.junit.Assert
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
class EnemyServiceImplTests {

    @Mock EnemyRepository enemyRepository

    @InjectMocks
    EnemyServiceImpl enemyService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenEnemy_whenEnemyRepositorySavesEnemy_thenEnemyServiceReturnsEnemy() {
        // Given
        Enemy enemy = new Enemy()

        // When
        enemyRepository.save(enemy)
        Mockito.when(enemyRepository.findById(enemy.identifier)).thenReturn(Optional.of(enemy))

        // Then
        Assert.assertEquals(enemyService.getEnemyById(enemy.identifier), enemy)
    }

}
