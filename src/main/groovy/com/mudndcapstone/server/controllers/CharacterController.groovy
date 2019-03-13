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
    ResponseEntity<Set<CharacterDto>> getAllCharacters() {
        Set<Character> characters = characterService.getAllCharacters()
        Set<CharacterDto> characterDtos = characterService.buildDtoSetFrom(characters)
        new ResponseEntity<>(characterDtos, HttpStatus.OK)
    }

    @PostMapping
    ResponseEntity<CharacterDto> createCharacter(@Valid @RequestBody CharacterDto characterDto) {
        Character characterRequest = characterService.buildCharacterFrom(characterDto)
        Character character = characterService.createCharacter(characterRequest)
        if (!character) return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)

        CharacterDto created = characterService.buildDtoFrom(character)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

    @GetMapping("/{characterId}")
    ResponseEntity<CharacterDto> getCharacterById(@PathVariable String characterId) {
        Character character = characterService.getCharacterById(characterId)
        if (!character) return new ResponseEntity(HttpStatus.BAD_REQUEST)

        CharacterDto characterDto = characterService.buildDtoFrom(character)
        new ResponseEntity<>(characterDto, HttpStatus.OK)
    }

    @PutMapping("/{characterId}")
    ResponseEntity<CharacterDto> updateCharacter(@PathVariable String characterId, @Valid @RequestBody CharacterDto characterDto) {
        new ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @DeleteMapping("/{characterId}")
    ResponseEntity deleteCharacter(@PathVariable String characterId) {
        characterService.deleteCharacter(characterId)
        new ResponseEntity(HttpStatus.OK)
    }

}
