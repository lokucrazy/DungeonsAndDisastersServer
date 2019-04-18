package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.dto.CombatDto
import com.mudndcapstone.server.repositories.CombatRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class CombatService {

    @Autowired CombatRepository combatRepository
    @Autowired SessionService sessionService
    @Autowired ModelMapper modelMapper

    Set<Combat> getAllCombats() {
        combatRepository.findAll().toSet()
    }

    Combat getCombatById(String id) {
        combatRepository.findById(id).orElse(null)
    }

    Combat createCombat(Combat combat) {
        combatRepository.save(combat)
    }

    Combat updateCombat(Combat combat) {
        if (!combat.identifier) return null
        combatRepository.save(combat)
    }

    void deleteCombat(String id) {
        combatRepository.deleteById(id)
    }

    Combat createCombatInSession(Combat combat) {
        if (!combat) return null
        Session session = combat.session

        if (session.combat == null) {
            combatRepository.save(combat)
        } else {
            combat.session = null
            Combat lastCombat = findLastCombat(session.combat)
            lastCombat.nextCombat = combat
            combatRepository.save(lastCombat)
            combat
        }
    }

    Combat findLastCombat(Combat combat) {
        combat = combatRepository.findById(combat.identifier).orElse(null)
        if (!combat) return null

        combat.nextCombat == null ? combat : findLastCombat(combat.nextCombat)
    }

    Combat insertCombatInPath(Session session, Combat newCombat) {
        if (!session || !newCombat) return null
        Combat prevCombat = combatRepository.findPreviousCombat(session.combat.identifier).orElse(null)
        Combat nextCombat = session.combat

        if (prevCombat) {
            prevCombat.nextCombat = newCombat
            combatRepository.save(prevCombat)
        }
        newCombat.nextCombat = nextCombat
        newCombat = combatRepository.save(newCombat)

        session.combat = newCombat
        sessionService.upsertSession(session)
        newCombat
    }

    Combat buildCombatFrom(CombatDto combatDto) {
        Combat combat = modelMapper.map(combatDto, Combat)
        combat.session = sessionService.getSessionById(combatDto.sessionId)
        combat
    }

    CombatDto buildDtoFrom(Combat combat) {
        CombatDto combatDto = modelMapper.map(combat, CombatDto)

        String previousCombatId = combat.nextCombat ? combat.nextCombat.identifier : null
        String sessionId = combat.session ? combat.session.identifier : null
        Set<String> enemyIds = combat.enemies ?
                combat.enemies.stream().map({ enemy -> enemy.identifier }).collect(Collectors.toSet()) :
                null

        combatDto.setNextCombatId(previousCombatId)
        combatDto.setSessionId(sessionId)
        combatDto.setEnemyIds(enemyIds)

        combatDto
    }

    Set<CombatDto> buildDtoSetFrom(Set<Combat> combats) {
        combats.stream().map({ combat -> buildDtoFrom(combat) }).collect(Collectors.toSet())
    }

}
