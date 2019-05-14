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
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.server.ResponseStatusException

import javax.print.DocFlavor.READER

@RunWith(SpringRunner)
@SpringBootTest
class CharacterControllerTests {

    @Mock CharacterService characterService
    @Mock UserService userService
    @Mock SessionService sessionService

    @InjectMocks
    CharacterController characterController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void getResponseEntity_whenGivenCharacterId_forGetCharacterById() {
        // Mocks
        Mockito.when(characterService.getCharacterById("test")).thenReturn(new Character())
        Mockito.when(characterService.buildDtoFrom(Mockito.any(Character))).thenReturn(new CharacterDto())

        // Test
        ResponseEntity<CharacterDto> responseEntity = characterController.getCharacterById("test")

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.OK
        assert responseEntity.body != null
    }

    @Test
    void getResponseStatusException_whenGivenBadCharacterId_forGetCharacterById() {
        // Mocks
        Mockito.when(characterService.getCharacterById("bad")).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            characterController.getCharacterById("bad")
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.NOT_FOUND
        assert responseStatusException.reason == Exceptions.CHARACTER_NOT_FOUND_EXCEPTION
    }

    @Test
    void getResponseEntity_whenGivenCharacterDto_forUpdateCharacter() {
        // Mocks
        Mockito.when(characterService.existsByCharacterId("test")).thenReturn(true)
        Mockito.when(userService.getUserById("user")).thenReturn(new User())
        Mockito.when(characterService.buildCharacterFrom(Mockito.any(CharacterDto), Mockito.any(User))).thenReturn(new Character())
        Mockito.when(characterService.upsertCharacter(Mockito.any(Character))).thenReturn(new Character())
        Mockito.when(characterService.buildDtoFrom(Mockito.any(Character))).thenReturn(new CharacterDto())

        // Test
        ResponseEntity<CharacterDto> responseEntity = characterController.updateCharacter("test", new CharacterDto(userId: "user"))

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.OK
        assert responseEntity.body != null
    }

    @Test
    void getResponseStatusException_whenGivenBadCharacterId_forUpdateCharacter() {
        // Mocks
        Mockito.when(characterService.existsByCharacterId("bad")).thenReturn(false)

        // Test
        ResponseStatusException responseStatusException
        try {
            characterController.updateCharacter("bad", null)
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.NOT_FOUND
        assert responseStatusException.reason == Exceptions.CHARACTER_NOT_FOUND_EXCEPTION
    }

    @Test
    void getResponseStatusException_whenGivenBadUserId_forUpdateCharacter() {
        // Mocks
        Mockito.when(characterService.existsByCharacterId("test")).thenReturn(true)
        Mockito.when(userService.getUserById("bad")).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            characterController.updateCharacter("test", new CharacterDto(userId: "bad"))
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.BAD_REQUEST
        assert responseStatusException.reason == Exceptions.USER_NOT_FOUND_EXCEPTION
    }

    @Test
    void getResponseStatusException_whenUpsertCharacterFails_forUpdateCharacter() {
        // Mocks
        Mockito.when(characterService.existsByCharacterId("test")).thenReturn(true)
        Mockito.when(userService.getUserById("user")).thenReturn(new User())
        Mockito.when(characterService.buildCharacterFrom(Mockito.any(CharacterDto), Mockito.any(User))).thenReturn(new Character())
        Mockito.when(characterService.upsertCharacter(Mockito.any(Character))).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            characterController.updateCharacter("test", new CharacterDto(userId: "user"))
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.INTERNAL_SERVER_ERROR
        assert responseStatusException.reason == Exceptions.CHARACTER_NOT_UPDATED_EXCEPTION
    }

    @Test
    void getResponseEntity_whenGivenCharacterId_forDeleteCharacter() {
        // Mocks
        Mockito.doNothing().when(characterService).deleteCharacter("test")

        // Test
        ResponseEntity responseEntity = characterController.deleteCharacter("test")

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.NO_CONTENT
    }

    @Test
    void getResponseEntity_whenGivenUserId_forCreateCharacter() {
        // Mocks
        Mockito.when(userService.getUserById("test")).thenReturn(new User())
        Mockito.when(characterService.buildAndCreateCharacter(Mockito.any(CharacterDto), Mockito.any(User))).thenReturn(new Character())
        Mockito.when(characterService.buildDtoFrom(Mockito.any(Character))).thenReturn(new CharacterDto())

        // Test
        ResponseEntity<CharacterDto> responseEntity = characterController.createCharacter("test", new CharacterDto())

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.OK
        assert responseEntity.body != null
    }

    @Test
    void getResponseStatusException_whenGivenBadUserId_forCreateCharacter() {
        // Mocks
        Mockito.when(userService.getUserById("bad")).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            characterController.createCharacter("bad", null)
        } catch(ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.BAD_REQUEST
        assert responseStatusException.reason == Exceptions.USER_NOT_FOUND_EXCEPTION
    }

    @Test
    void getResponseStatusException_whenBuildAndCreateFails_forCreateCharacter() {
        // Mocks
        Mockito.when(userService.getUserById("test")).thenReturn(new User())
        Mockito.when(characterService.buildAndCreateCharacter(Mockito.any(CharacterDto), Mockito.any(User))).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            characterController.createCharacter("test", null)
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.INTERNAL_SERVER_ERROR
        assert responseStatusException.reason == Exceptions.CHARACTER_NOT_CREATED_EXCEPTION
    }

    @Test
    void getResponseEntity_whenGivenUserId_forGetAllUsersCharacters() {
        // Mocks
        Mockito.when(userService.getUserById("test")).thenReturn(new User())
        Mockito.when(characterService.buildDtoSetFrom(Mockito.any(Set))).thenReturn(new HashSet<CharacterDto>())

        // Test
        ResponseEntity<Set<CharacterDto>> responseEntity = characterController.getAllUsersCharacters("test")

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.OK
        assert responseEntity.body != null
    }

    @Test
    void getResponseStatusException_whenGivenBadUserId_forGetAllUsersCharacters() {
        // Mocks
        Mockito.when(userService.getUserById("bad")).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            characterController.getAllUsersCharacters("bad")
        } catch(ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.BAD_REQUEST
        assert responseStatusException.reason == Exceptions.USER_NOT_FOUND_EXCEPTION
    }

    @Test
    void getResponseEntity_whenGivenSessionId_forGetAllSessionsCharacters() {
        // Mocks
        Mockito.when(sessionService.getSessionById("test")).thenReturn(new Session())
        Mockito.when(characterService.buildDtoSetFrom(Mockito.any(Set))).thenReturn(new HashSet<CharacterDto>())

        // Test
        ResponseEntity<Set<CharacterDto>> responseEntity = characterController.getAllSessionsCharacters("test")

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.OK
        assert responseEntity.body != null
    }

    @Test
    void GetResponseStatusException_whenGivenBadSessionId_forGetAllSessionsCharacters() {
        // Mocks
        Mockito.when(sessionService.getSessionById("bad")).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            characterController.getAllSessionsCharacters("bad")
        } catch(ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.BAD_REQUEST
        assert responseStatusException.reason == Exceptions.SESSION_NOT_FOUND_EXCEPTION
    }

    @Test
    void getResponseEntity_whenGivenSessionIdAndCharacterId_forConnectCharacterToSession() {
        // Mocks
        Mockito.when(sessionService.getSessionById("session")).thenReturn(new Session())
        Mockito.when(characterService.getCharacterById("character")).thenReturn(new Character())
        Mockito.when(sessionService.attachCharacterToSession(Mockito.any(Session), Mockito.any(Character))).thenReturn(new Session())
        Mockito.when(sessionService.buildDtoFrom(Mockito.any(Session))).thenReturn(new SessionDto())

        // Test
        ResponseEntity<SessionDto> responseEntity = characterController.connectCharacterToSession("session", "character")

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.OK
        assert responseEntity.body != null
    }

    @Test
    void getResponseStatusException_whenGivenBadSessionId_forConnectCharacterToSession() {
        // Mocks
        Mockito.when(sessionService.getSessionById("bad")).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            characterController.connectCharacterToSession("bad", "character")
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.BAD_REQUEST
        assert responseStatusException.reason == Exceptions.SESSION_NOT_FOUND_EXCEPTION
    }

    @Test
    void getResponseStatusException_whenGivenBadCharacterId_forConnectionCharacterToSession() {
        // Mocks
        Mockito.when(sessionService.getSessionById("session")).thenReturn(new Session())
        Mockito.when(characterService.getCharacterById("bad")).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            characterController.connectCharacterToSession("session", "bad")
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.BAD_REQUEST
        assert responseStatusException.reason == Exceptions.CHARACTER_NOT_FOUND_EXCEPTION
    }

    @Test
    void getResponseStatusException_whenAttachCharacterToSessionFails_forConnectCharacterToSEession() {
        // Mocks
        Mockito.when(sessionService.getSessionById("session")).thenReturn(new Session())
        Mockito.when(characterService.getCharacterById("character")).thenReturn(new Character())
        Mockito.when(sessionService.attachCharacterToSession(Mockito.any(Session), Mockito.any(Character))).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            characterController.connectCharacterToSession("session", "character")
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.INTERNAL_SERVER_ERROR
        assert responseStatusException.reason == Exceptions.SESSION_CANT_CONNECT_CHARACTER_EXCEPTION
    }
}
