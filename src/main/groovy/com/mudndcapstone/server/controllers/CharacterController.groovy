package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.models.dto.SessionDto
import com.mudndcapstone.server.services.CharacterService
import com.mudndcapstone.server.services.SessionService
import com.mudndcapstone.server.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

@RestController
class CharacterController {

    @Autowired CharacterService characterService
    @Autowired SessionService sessionService
    @Autowired UserService userService

    @GetMapping("/characters")
    ResponseEntity<Set<CharacterDto>> getAllCharacters() {
        Set<Character> characters = characterService.getAllCharacters()
        Set<CharacterDto> characterDtos = characterService.buildDtoSetFrom(characters)
        new ResponseEntity<>(characterDtos, HttpStatus.OK)
    }

    @PostMapping("/characters")
    ResponseEntity<CharacterDto> createCharacter(@Valid @RequestBody CharacterDto characterDto) {
        Character characterRequest = characterService.buildCharacterFrom(characterDto)
        Character character = characterService.createCharacter(characterRequest)
        if (!character) return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)

        CharacterDto created = characterService.buildDtoFrom(character)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

    @PutMapping("/sessions/{sessionId}/characters/{characterId}")
    ResponseEntity<SessionDto> connectCharacterToSession(@PathVariable String sessionId, @PathVariable String characterId) {
        if (!sessionId || !characterId) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sessionId or characterId could not be found")

        Session session = sessionService.getSessionById(sessionId)
        Character character = characterService.getCharacterById(characterId)
        if (!session || !character) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "session or character could not be found")

        session = sessionService.attachCharacterToSession(session, character)
        if (!session) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "character could not be added to session")

        SessionDto sessionDto = sessionService.buildDtoFrom(session)
        new ResponseEntity<>(sessionDto, HttpStatus.OK)
    }

    @GetMapping("/characters/{characterId}")
    ResponseEntity<CharacterDto> getCharacterById(@PathVariable String characterId) {
        Character character = characterService.getCharacterById(characterId)
        if (!character) return new ResponseEntity(HttpStatus.BAD_REQUEST)

        CharacterDto characterDto = characterService.buildDtoFrom(character)
        new ResponseEntity<>(characterDto, HttpStatus.OK)
    }

    @PutMapping("/characters/{characterId}")
    ResponseEntity<CharacterDto> updateCharacter(@PathVariable String characterId, @Valid @RequestBody CharacterDto characterDto) {
        new ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @DeleteMapping("/characters/{characterId}")
    ResponseEntity deleteCharacter(@PathVariable String characterId) {
        characterService.deleteCharacter(characterId)
        new ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/sessions/{sessionId}/characters")
    ResponseEntity<Set<CharacterDto>> getAllSessionsCharacters(@PathVariable String sessionId) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) return new ResponseEntity<>(HttpStatus.BAD_REQUEST)
        if (!session.characters) return new ResponseEntity<>([], HttpStatus.OK)

        Set<CharacterDto> characterDtos = characterService.buildDtoSetFrom(session.characters)
        new ResponseEntity<>(characterDtos, HttpStatus.OK)
    }

    @GetMapping("/users/{userId}/characters")
    ResponseEntity<Set<CharacterDto>> getAllUsersCharacters(@PathVariable String userId) {
        User user = userService.getUserById(userId)
        if (!user) return new ResponseEntity<>(HttpStatus.BAD_REQUEST)
        if (!user.characters) return new ResponseEntity<>([], HttpStatus.OK)

        Set<CharacterDto> characterDtos = characterService.buildDtoSetFrom(user.characters)
        new ResponseEntity<>(characterDtos, HttpStatus.OK)
    }

}
