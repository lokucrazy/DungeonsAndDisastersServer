package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.dto.CombatDto
import com.mudndcapstone.server.repositories.CombatRepository
import com.mudndcapstone.server.services.CombatService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class CombatServiceImpl implements CombatService {

    @Autowired CombatRepository combatRepository
    @Autowired ModelMapper modelMapper

    @Override
    List<Combat> getAllCombats() {
        combatRepository.findAll().asList()
    }

    @Override
    Combat getCombatById(Long id) {
        combatRepository.findById(id).orElse(null)
    }

    @Override
    Combat createCombat(Combat combat) {
        combatRepository.save(combat)
    }

    @Override
    void deleteCombat(Long id) {
        combatRepository.deleteById(id)
    }

    Combat buildCombatFrom(CombatDto combatDto) {
        Combat combat = modelMapper.map(combatDto, Combat)

        combat
    }

    CombatDto buildDtoFrom(Combat combat) {
        CombatDto combatDto = modelMapper.map(combat, CombatDto)

        Long previousCombatId = combat.previousCombat ? combat.previousCombat.identifier : null
        Long sessionId = combat.session ? combat.session.identifier : null
        List<Long> enemyIds = combat.enemies ?
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
