package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.dto.CombatDto
import com.mudndcapstone.server.services.CombatService
import com.mudndcapstone.server.services.SessionService
import com.mudndcapstone.server.utils.Exceptions
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
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
class CombatController {

    @Autowired CombatService combatService
    @Autowired SessionService sessionService

    @GetMapping("/combats")
    ResponseEntity<Set<CombatDto>> getAllCombats() {
        Set<Combat> combats = combatService.getAllCombats()
        Set<CombatDto> combatDtos = combatService.buildDtoSetFrom(combats)
        new ResponseEntity<>(combatDtos, HttpStatus.OK)
    }

    @Transactional(rollbackFor = ResponseStatusException)
    @RequestMapping(value = "/combats", method = [RequestMethod.PUT, RequestMethod.POST])
    ResponseEntity<CombatDto> createCombat(@Valid @RequestBody CombatDto combatDto, HttpServletRequest request) {
        Session session = sessionService.getSessionById(combatDto.sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.SESSION_NOT_FOUND_EXCEPTION)
        Combat combat = null

        Combat combatRequest = combatService.buildCombatFrom(combatDto, session)
        if (request.getMethod() == "PUT") {
            combat = combatService.appendCombatToPath(combatRequest, session)
        } else if (request.getMethod() == "POST") {
            combat = combatService.insertCombatToPath(combatRequest, session)
        }
        if (!combat) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.COMBAT_NOT_CREATED_EXCEPTION)

        CombatDto created = combatService.buildDtoFrom(combat)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

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
}
