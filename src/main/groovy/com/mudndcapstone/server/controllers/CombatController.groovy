package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.State
import com.mudndcapstone.server.models.dto.CombatDto
import com.mudndcapstone.server.models.dto.EnemyDto
import com.mudndcapstone.server.services.CombatService
import com.mudndcapstone.server.services.SessionService
import com.mudndcapstone.server.utils.Exceptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

@RestController
@CrossOrigin("*")
class CombatController {

    @Autowired CombatService combatService
    @Autowired SessionService sessionService

    @GetMapping("/combats/{combatId}")
    ResponseEntity<CombatDto> getCombatById(@PathVariable String combatId) {
        Combat combat = combatService.getCombatById(combatId)
        if (!combat) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.COMBAT_NOT_FOUND_EXCEPTION)

        CombatDto combatDto = combatService.buildDtoFrom(combat)
        new ResponseEntity<>(combatDto, HttpStatus.OK)
    }

    @PutMapping("/combats/{combatId}")
    ResponseEntity<CombatDto> updateCombat(@PathVariable String combatId, @Valid @RequestBody CombatDto combatDto) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, Exceptions.ROUTE_NOT_IMPLEMENTED)
    }

    @DeleteMapping("/combats/{combatId}")
    ResponseEntity deleteCombat(@PathVariable String combatId) {
        combatService.deleteCombat(combatId)
        new ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PatchMapping("/sessions/{sessionId}/combats/state")
    ResponseEntity<CombatDto> setCombatState(@PathVariable String sessionId, @Valid @RequestBody State state) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.SESSION_NOT_FOUND_EXCEPTION)

        Combat updated = combatService.setCombatState(session.combat, state)
        CombatDto combatDto = combatService.buildDtoFrom(updated)
        new ResponseEntity<>(combatDto, HttpStatus.OK)
    }

    @Transactional(rollbackFor = ResponseStatusException)
    @PostMapping("/sessions/{sessionId}/combats")
    ResponseEntity<CombatDto> createCombat(@PathVariable String sessionId, @Valid @RequestBody CombatDto combatDto, @RequestParam boolean insert) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.SESSION_NOT_FOUND_EXCEPTION)

        Combat combat = combatService.buildAndCreateCombat(combatDto, session, insert)
        if (!combat) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.COMBAT_NOT_CREATED_EXCEPTION)

        CombatDto created = combatService.buildDtoFrom(combat)
        new ResponseEntity<>(created, HttpStatus.OK)
    }


}
