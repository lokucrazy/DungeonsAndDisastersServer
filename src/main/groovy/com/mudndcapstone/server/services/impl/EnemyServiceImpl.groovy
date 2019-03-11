package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.models.dto.EnemyDto
import com.mudndcapstone.server.repositories.EnemyRepository
import com.mudndcapstone.server.services.EnemyService
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

    Enemy getEnemyById(Long id) {
        enemyRepository.findById(id).orElse(null)
    }

    Enemy createEnemy(Enemy enemy) {
        enemyRepository.save(enemy)
    }

    void deleteEnemy(Long id) {
        enemyRepository.deleteById(id)
    }

    Enemy buildEnemyFrom(EnemyDto enemyDto) {
        Enemy enemy = modelMapper.map(enemyDto, Enemy)

        enemy
    }

    EnemyDto buildDtoFrom(Enemy enemy) {
        EnemyDto enemyDto = modelMapper.map(enemy, EnemyDto)

        Long combatId = enemy.combat ? enemy.combat.identifier : null

        enemyDto.setCombatId(combatId)

        enemyDto
    }

    Set<EnemyDto> buildDtoSetFrom(Set<Enemy> enemys) {
        enemys.stream().map({ enemy -> buildDtoFrom(enemy) }).collect(Collectors.toSet())
    }

}
