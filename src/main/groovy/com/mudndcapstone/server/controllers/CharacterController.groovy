package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.models.dto.SessionDto
import com.mudndcapstone.server.services.CharacterService
import com.mudndcapstone.server.services.SessionService
import com.mudndcapstone.server.services.UserService
import com.mudndcapstone.server.utils.Exceptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

@RestController
@CrossOrigin("*")
class CharacterController {

    @Autowired CharacterService characterService
    @Autowired SessionService sessionService
    @Autowired UserService userService

    @GetMapping("/characters/{characterId}")
    ResponseEntity<CharacterDto> getCharacterById(@PathVariable String characterId) {
        Character character = characterService.getCharacterById(characterId)
        if (!character) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.CHARACTER_NOT_FOUND_EXCEPTION)

        CharacterDto characterDto = characterService.buildDtoFrom(character)
        new ResponseEntity<>(characterDto, HttpStatus.OK)
    }

    @PutMapping("/characters/{characterId}")
    ResponseEntity<CharacterDto> updateCharacter(@PathVariable String characterId, @Valid @RequestBody CharacterDto characterDto) {
        if (!characterService.existsByCharacterId(characterId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.CHARACTER_NOT_FOUND_EXCEPTION)
        User user = userService.getUserById(characterDto.userId)
        if (!user) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.USER_NOT_FOUND_EXCEPTION)
        Character characterRequest = characterService.buildCharacterFrom(characterDto, user)
        characterRequest.identifier = characterId
        Character character

        character = characterService.upsertCharacter(characterRequest)
        if (!character) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.CHARACTER_NOT_UPDATED_EXCEPTION)

        CharacterDto updated = characterService.buildDtoFrom(character)
        new ResponseEntity<>(updated, HttpStatus.OK)
    }

    @DeleteMapping("/characters/{characterId}")
    ResponseEntity deleteCharacter(@PathVariable String characterId) {
        characterService.deleteCharacter(characterId)
        new ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PostMapping("/users/{userId}/characters")
    ResponseEntity<CharacterDto> createCharacter(@PathVariable String userId, @Valid @RequestBody CharacterDto characterDto) {
        User user = userService.getUserById(userId)
        if (!user) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.USER_NOT_FOUND_EXCEPTION)

        Character character = characterService.buildAndCreateCharacter(characterDto, user)
        if (!character) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.CHARACTER_NOT_CREATED_EXCEPTION)
      
        CharacterDto created = characterService.buildDtoFrom(character)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

    @GetMapping("/users/{userId}/characters")
    ResponseEntity<Set<CharacterDto>> getAllUsersCharacters(@PathVariable String userId) {
        User user = userService.getUserById(userId)
        if (!user) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.USER_NOT_FOUND_EXCEPTION)

        Set<CharacterDto> characterDtos = characterService.buildDtoSetFrom(user.characters)
        new ResponseEntity<>(characterDtos, HttpStatus.OK)
    }

    @GetMapping("/sessions/{sessionId}/characters")
    ResponseEntity<Set<CharacterDto>> getAllSessionsCharacters(@PathVariable String sessionId) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.SESSION_NOT_FOUND_EXCEPTION)

        Set<CharacterDto> characterDtos = characterService.buildDtoSetFrom(session.characters)
        new ResponseEntity<>(characterDtos, HttpStatus.OK)
    }

    @PutMapping("/sessions/{sessionId}/characters/{characterId}")
    ResponseEntity<SessionDto> connectCharacterToSession(@PathVariable String sessionId, @PathVariable String characterId) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.SESSION_NOT_FOUND_EXCEPTION)

        Character character = characterService.getCharacterById(characterId)
        if (!character) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.CHARACTER_NOT_FOUND_EXCEPTION)

        session = sessionService.attachCharacterToSession(session, character)
        if (!session) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.SESSION_CANT_CONNECT_CHARACTER_EXCEPTION)

        SessionDto sessionDto = sessionService.buildDtoFrom(session)
        new ResponseEntity<>(sessionDto, HttpStatus.OK)
    }

}
