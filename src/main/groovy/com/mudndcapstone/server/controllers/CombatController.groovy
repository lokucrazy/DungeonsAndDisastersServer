package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.dto.CombatDto
import com.mudndcapstone.server.services.impl.CombatServiceImpl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid

@RestController
@RequestMapping("/combats")
class CombatController {

    @Autowired CombatServiceImpl combatService

    @GetMapping
    ResponseEntity<List<CombatDto>> getAllCombats() {
        List<Combat> combats = combatService.getAllCombats()
        List<CombatDto> combatDtos = combatService.buildDtoListFrom(combats)
        new ResponseEntity<>(combatDtos, HttpStatus.OK)
    }

    @PostMapping
    ResponseEntity<CombatDto> createCombat(@Valid @RequestBody CombatDto combatDto) {
        Combat combatRequest = combatService.buildCombatFrom(combatDto)
        Combat combat = combatService.createCombat(combatRequest)
        if (!combat) return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)

        CombatDto created = combatService.buildDtoFrom(combat)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

    @GetMapping("/{combatId}")
    ResponseEntity<CombatDto> getCombatById(@PathVariable Long combatId) {
        Combat combat = combatService.getCombatById(combatId)
        if (!combat) return new ResponseEntity(HttpStatus.BAD_REQUEST)

        CombatDto combatDto = combatService.buildDtoFrom(combat)
        new ResponseEntity<>(combatDto, HttpStatus.OK)
    }

    @PutMapping("/{combatId}")
    ResponseEntity<CombatDto> updateCombat(@PathVariable Long combatId, @Valid @RequestBody CombatDto combatDto) {
        new ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @DeleteMapping("/{combatId}")
    ResponseEntity deleteCombat(@PathVariable Long combatId) {
        combatService.deleteCombat(combatId)
        new ResponseEntity(HttpStatus.OK)
    }

}
