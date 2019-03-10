package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.services.impl.CharacterServiceImpl
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
@RequestMapping("/characters")
class CharacterController {

    @Autowired CharacterServiceImpl characterService

    @GetMapping
    ResponseEntity<List<CharacterDto>> getAllCharacters() {
        List<Character> characters = characterService.getAllCharacters()
        List<CharacterDto> characterDtos = characterService.buildDtoListFrom(characters)
        new ResponseEntity<>(characterDtos, HttpStatus.OK)
    }

    @PostMapping
    ResponseEntity<CharacterDto> createCharacter(@Valid @RequestBody CharacterDto characterDto) {
        Character characterRequest = characterService.buildCharacterFrom(characterDto)
        Character character = characterService.createCharacter(characterRequest)
        CharacterDto created = characterService.buildDtoFrom(character)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

    @GetMapping("/{characterId}")
    ResponseEntity<CharacterDto> getCharacterById(@PathVariable Long characterId) {
        Character character = characterService.getCharacterById(characterId)
        CharacterDto characterDto = characterService.buildDtoFrom(character)
        new ResponseEntity<>(characterDto, HttpStatus.OK)
    }

    @PutMapping("/{characterId}")
    ResponseEntity<CharacterDto> updateCharacter(@PathVariable Long characterId, @Valid @RequestBody CharacterDto characterDto) {
        new ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @DeleteMapping("/{characterId}")
    ResponseEntity deleteCharacter(@PathVariable Long characterId) {
        characterService.deleteCharacter(characterId)
        new ResponseEntity(HttpStatus.OK)
    }

}
