package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.Session
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

    Combat getCombatById(String id) {
        combatRepository.findById(id).orElse(null)
    }

    Combat createCombat(Combat combat) {
        Auditor.enableAuditing(combat)
        combatRepository.save(combat)
    }

    Combat buildAndCreateCombat(CombatDto combatDto, Session session, boolean insert = false) {
        Combat combatRequest = buildCombatFrom(combatDto, session)
        Combat combat = insert ? insertCombatToPath(combatRequest, session) : appendCombatToPath(combatRequest, session)
        combat
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
        Combat prevCombat = combatRepository.findPreviousCombat(session.combat.identifier).orElse(null)
        Combat nextCombat = session.combat

        if (prevCombat) {
            prevCombat.nextCombat = newCombat
            Auditor.updateAuditing(prevCombat)
            combatRepository.save(prevCombat)
        }
        newCombat.nextCombat = nextCombat
        Auditor.updateAuditing(newCombat)
        newCombat = combatRepository.save(newCombat)
        session.combat = newCombat
        sessionService.upsertSession(session)
        newCombat
    }

    Combat findLastCombat(Combat combat) {
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
