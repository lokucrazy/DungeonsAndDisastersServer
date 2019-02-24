package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.services.CombatService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/combats")
class CombatController {

    @Autowired CombatService combatService

    @GetMapping
    ResponseEntity<List<Combat>> getAllCombats() {
        List<Combat> allCombats = combatService.getAllCombats()
        new ResponseEntity<List<Combat>>(allCombats, HttpStatus.OK)
    }
}
