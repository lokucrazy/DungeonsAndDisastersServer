package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.dto.CombatDto
import com.mudndcapstone.server.services.CombatService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

@RestController
@RequestMapping("/combats")
class CombatController {

    @Autowired CombatService combatService

    @GetMapping
    ResponseEntity<Set<CombatDto>> getAllCombats() {
        Set<Combat> combats = combatService.getAllCombats()
        Set<CombatDto> combatDtos = combatService.buildDtoSetFrom(combats)
        new ResponseEntity<>(combatDtos, HttpStatus.OK)
    }

    @Transactional(rollbackFor = ResponseStatusException)
    @PostMapping
    ResponseEntity<CombatDto> createCombat(@Valid @RequestBody CombatDto combatDto) {
        if (!combatDto.sessionId) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "session id could not be found")
        Combat combatRequest = combatService.buildCombatFrom(combatDto)
        Combat combat

        if (!combatRequest.identifier) {
            if (combatRequest.session) {
                combat = combatService.createCombat(combatRequest)
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "session does not exist, or has been turned into a history node")
            }
        } else {
            Combat lastCombat = combatService.getLastNode(combatRequest.identifier)
            combatRequest.session = null
            combatRequest.identifier = null
            if (lastCombat) {
                lastCombat.nextCombat = combatService.createCombat(combatRequest)
                combat = combatService.updateCombat(lastCombat).nextCombat
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "combat identifier does not exist")
            }
        }
        if (!combat) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "combat could not be created")

        CombatDto created = combatService.buildDtoFrom(combat)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

    @GetMapping("/{combatId}")
    ResponseEntity<CombatDto> getCombatById(@PathVariable String combatId) {
        Combat combat = combatService.getCombatById(combatId)
        if (!combat) return new ResponseEntity(HttpStatus.BAD_REQUEST)

        CombatDto combatDto = combatService.buildDtoFrom(combat)
        new ResponseEntity<>(combatDto, HttpStatus.OK)
    }

    @PutMapping("/{combatId}")
    ResponseEntity<CombatDto> updateCombat(@PathVariable String combatId, @Valid @RequestBody CombatDto combatDto) {
        new ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @DeleteMapping("/{combatId}")
    ResponseEntity deleteCombat(@PathVariable String combatId) {
        combatService.deleteCombat(combatId)
        new ResponseEntity(HttpStatus.OK)
    }

}
