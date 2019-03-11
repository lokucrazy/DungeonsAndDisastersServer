package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Enemy

interface EnemyService {
    List<Enemy> getAllEnemies()
    Enemy getEnemyById(Long id)
    Enemy createEnemy(Enemy enemy)
    void deleteEnemy(Long id)
}