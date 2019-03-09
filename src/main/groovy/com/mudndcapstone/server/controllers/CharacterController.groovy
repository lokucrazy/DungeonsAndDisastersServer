package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.services.CharacterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/characters")
class CharacterController {

    @Autowired CharacterService characterService

    @GetMapping
    ResponseEntity<List<Character>> getAllCharacters() {
        List<Character> allCharacters = characterService.getAllCharacters()
        new ResponseEntity<>(allCharacters, HttpStatus.OK)
    }

}
