package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.models.request.EnemyRequest
import com.mudndcapstone.server.repositories.EnemyRepository
import com.mudndcapstone.server.services.EnemyService
import com.mudndcapstone.server.utils.ModelBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
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
    Enemy createEnemy(EnemyRequest enemyRequest) {
        Enemy enemy = ModelBuilder.buildEnemyFrom(enemyRequest)
        enemyRepository.save(enemy)
    }

    @Override
    void deleteEnemy(Long id) {
        enemyRepository.deleteById(id)
    }
}
