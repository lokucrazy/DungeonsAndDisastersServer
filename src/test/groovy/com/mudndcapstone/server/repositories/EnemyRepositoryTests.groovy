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

    @Autowired
    private EnemyRepository enemyRepository

    @Test
    void whenFindById_returnEnemy() {
        //Given
        Enemy enemy = new Enemy()
        enemyRepository.save(enemy)
        //When
        Optional<Enemy> found = enemyRepository.findById(enemy.id)
        //Then
        Assert.assertEquals(found.get().id, enemy.id)
    }
}
