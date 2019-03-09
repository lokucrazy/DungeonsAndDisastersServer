package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.models.request.EnemyRequest

interface EnemyService {
    List<Enemy> getAllEnemies()
    Enemy getEnemyById(Long id)
    Enemy createEnemy(EnemyRequest enemy)
    void deleteEnemy(Long id)
}