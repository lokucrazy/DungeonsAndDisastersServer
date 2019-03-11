package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.dto.CombatDto
import com.mudndcapstone.server.repositories.CombatRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class CombatServiceImpl {

    @Autowired CombatRepository combatRepository
    @Autowired ModelMapper modelMapper

    List<Combat> getAllCombats() {
        combatRepository.findAll().asList()
    }

    Combat getCombatById(String id) {
        combatRepository.findById(id).orElse(null)
    }

    Combat createCombat(Combat combat) {
        combatRepository.save(combat)
    }

    void deleteCombat(String id) {
        combatRepository.deleteById(id)
    }

    Combat buildCombatFrom(CombatDto combatDto) {
        Combat combat = modelMapper.map(combatDto, Combat)
        combat
    }

    CombatDto buildDtoFrom(Combat combat) {
        CombatDto combatDto = modelMapper.map(combat, CombatDto)

        String previousCombatId = combat.previousCombat ? combat.previousCombat.identifier : null
        String sessionId = combat.session ? combat.session.identifier : null
        List<String> enemyIds = combat.enemies ?
                combat.enemies.stream().map({ enemy -> enemy.identifier }).collect(Collectors.toList()) :
                null

        combatDto.setPreviousCombatId(previousCombatId)
        combatDto.setSessionId(sessionId)
        combatDto.setEnemyIds(enemyIds)

        combatDto
    }

    List<CombatDto> buildDtoListFrom(List<Combat> combats) {
        combats.stream().map({ combat -> buildDtoFrom(combat) }).collect(Collectors.toList())
    }

}
