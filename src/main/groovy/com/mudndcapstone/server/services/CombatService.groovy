package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.State
import com.mudndcapstone.server.models.dto.CombatDto
import com.mudndcapstone.server.repositories.CombatRepository
import com.mudndcapstone.server.utils.Auditor
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

    Combat setCombatState(Combat combat, State state) {
        if (combat ==  null) return null
        combat = combatRepository.findById(combat.identifier).orElse(combat)

        if (state.running) {
            combat.running = true
        } else {
            combat.running = false
            Session session = combat.session
            session.combat = combat.nextCombat
            combat.session = null
            sessionService.upsertSession(session)
        }

        Auditor.updateAuditing(combat)
        combat
    }

    Combat getCombatById(String id) {
        combatRepository.findById(id).orElse(null)
    }

    Combat upsertCombat(Combat combat) {
        Auditor.enableAuditing(combat)
        combatRepository.save(combat)
    }

    Combat buildAndCreateCombat(CombatDto combatDto, Session session, boolean insert = false) {
        Combat combatRequest = buildCombatFrom(combatDto, session)

        insert ? insertCombatToPath(combatRequest, session) : appendCombatToPath(combatRequest, session)
    }

    Combat updateCombat(Combat combat) {
        if (!combat.identifier) return null

        Auditor.updateAuditing(combat)
        combatRepository.save(combat)
    }

    void deleteCombat(String id) {
        combatRepository.deleteById(id)
    }

    Combat appendCombatToPath(Combat combat, Session session) {
        if (!combat || !session) return null
        if (!session.combat) return combatRepository.save(combat)

        combat.session = null
        Combat lastCombat = findLastCombat(session.combat)
        lastCombat.nextCombat = combat

        Auditor.updateAuditing(lastCombat)
        combatRepository.save(lastCombat)

        combat
    }

    Combat insertCombatToPath(Combat newCombat, Session session) {
        if (!session || !newCombat) return null
        String prevCombatId = combatRepository.findPreviousCombatId(session.combat.identifier).orElse("")
        Combat prevCombat = combatRepository.findById(prevCombatId).orElse(null)
        Combat nextCombat = session.combat

        if (prevCombat) {
            prevCombat.nextCombat = newCombat
            Auditor.updateAuditing(prevCombat)
            combatRepository.save(prevCombat)
        }
        newCombat.nextCombat = nextCombat
        session.combat = newCombat
        nextCombat.session = null

        Auditor.updateAuditing(newCombat)
        combatRepository.save(newCombat)
    }

    Combat findLastCombat(Combat combat) {
        combat = combatRepository.findById(combat.identifier).orElse(combat)
        if (!combat.nextCombat) return combat
        return findLastCombat(combat.nextCombat)
    }

    Combat buildCombatFrom(CombatDto combatDto, Session session) {
        Combat combat = modelMapper.map(combatDto, Combat)

        combat.session = session

        combat
    }

    CombatDto buildDtoFrom(Combat combat) {
        CombatDto combatDto = modelMapper.map(combat, CombatDto)

        String nextCombatId = combat.nextCombat ? combat.nextCombat.identifier : null
        String sessionId = combat.session ? combat.session.identifier : null
        Set<String> enemyIds = combat.enemies ?
                combat.enemies.stream().map({ enemy -> enemy.identifier }).collect(Collectors.toSet()) :
                null

        combatDto.setNextCombatId(nextCombatId)
        combatDto.setSessionId(sessionId)
        combatDto.setEnemyIds(enemyIds)

        combatDto
    }

    Set<CombatDto> buildDtoSetFrom(Set<Combat> combats) {
        if (!combats) return []
        combats.stream().map({ combat -> buildDtoFrom(combat) }).collect(Collectors.toSet())
    }

}
