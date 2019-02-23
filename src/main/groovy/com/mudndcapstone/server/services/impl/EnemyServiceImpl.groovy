package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.repositories.EnemyRepository
import com.mudndcapstone.server.services.EnemyService
import org.springframework.beans.factory.annotation.Autowired

class EnemyServiceImpl implements EnemyService {

    @Autowired EnemyRepository enemyRepository

    @Override
    List<Enemy> getAllEnemies() {
        enemyRepository.findAll().asList()
    }

    @Override
    Enemy getEnemyById(Long id) {
        enemyRepository.findById(id).orElse(null)
    }

    @Override
    Enemy createEnemy(Enemy enemy) {
        enemyRepository.save(enemy)
    }

    @Override
    void deleteEnemy(Long id) {
        enemyRepository.deleteById(id)
    }
}
