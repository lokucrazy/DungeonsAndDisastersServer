package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.EnemyDto
import com.mudndcapstone.server.repositories.EnemyRepository
import com.mudndcapstone.server.utils.Auditor
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class EnemyService {

    @Autowired EnemyRepository enemyRepository
    @Autowired ModelMapper modelMapper

    Set<Enemy> getAllEnemies() {
        enemyRepository.findAll().toSet()
    }

    Enemy getEnemyById(String id) {
        enemyRepository.findById(id).orElse(null)
    }

    Enemy createEnemy(Enemy enemy) {
        Auditor.enableAuditing(enemy)
        enemyRepository.save(enemy)
    }

    Enemy buildAndCreateEnemy(EnemyDto enemyDto, Session session, User dm) {
        Enemy enemyRequest = buildEnemyFrom(enemyDto, session, dm)
        Enemy enemy = createEnemy(enemyRequest)
        enemy
    }

    void deleteEnemy(String id) {
        enemyRepository.deleteById(id)
    }

    Enemy buildEnemyFrom(EnemyDto enemyDto, Session session, User dm) {
        Enemy enemy = modelMapper.map(enemyDto, Enemy)

        enemy.session = session
        enemy.dm = dm

        enemy
    }

    EnemyDto buildDtoFrom(Enemy enemy) {
        EnemyDto enemyDto = modelMapper.map(enemy, EnemyDto)

        String combatId = enemy.combat ? enemy.combat.identifier : null
        enemyDto.setCombatId(combatId)

        enemyDto
    }

    Set<EnemyDto> buildDtoSetFrom(Set<Enemy> enemies) {
        if (!enemies) return []
        enemies.stream().map({ enemy -> buildDtoFrom(enemy) }).collect(Collectors.toSet())
    }

}
