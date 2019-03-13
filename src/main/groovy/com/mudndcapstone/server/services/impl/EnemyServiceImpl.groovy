package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.models.dto.EnemyDto
import com.mudndcapstone.server.repositories.EnemyRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class EnemyServiceImpl {

    @Autowired EnemyRepository enemyRepository
    @Autowired ModelMapper modelMapper

    Set<Enemy> getAllEnemies() {
        enemyRepository.findAll().toSet()
    }

    Enemy getEnemyById(String id) {
        enemyRepository.findById(id).orElse(null)
    }

    Enemy createEnemy(Enemy enemy) {
        enemyRepository.save(enemy)
    }

    void deleteEnemy(String id) {
        enemyRepository.deleteById(id)
    }

    Enemy buildEnemyFrom(EnemyDto enemyDto) {
        Enemy enemy = modelMapper.map(enemyDto, Enemy)
        enemy
    }

    EnemyDto buildDtoFrom(Enemy enemy) {
        EnemyDto enemyDto = modelMapper.map(enemy, EnemyDto)

        String combatId = enemy.combat ? enemy.combat.identifier : null
        enemyDto.setCombatId(combatId)

        enemyDto
    }

    Set<EnemyDto> buildDtoSetFrom(Set<Enemy> enemys) {
        enemys.stream().map({ enemy -> buildDtoFrom(enemy) }).collect(Collectors.toSet())
    }

}
