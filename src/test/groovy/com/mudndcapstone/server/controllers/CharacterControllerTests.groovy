package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.services.CharacterService
import com.mudndcapstone.server.services.SessionService
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

@RunWith(SpringRunner)
@SpringBootTest
class CharacterControllerTests {

    @Mock CharacterService characterService
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
    void givenSession_whenSessionHasCharacters_thenSessionControllerReturnsCharacters() {
        // Given
        Session session = new Session()
        String testUuid = UUID.randomUUID().toString()
        HashSet<Character> characters = [new Character(), new Character()]
        Set<CharacterDto> characterDtos
        ResponseEntity response

        // When
        session.setIdentifier(testUuid)
        session.setCharacters(characters)
        characterDtos = characterService.buildDtoSetFrom(session.characters)
        Mockito.when(sessionService.getSessionById(testUuid)).thenReturn(session)
        response = characterController.getAllSessionsCharacters(testUuid)

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == characterDtos
        Mockito.verify(sessionService, Mockito.atLeastOnce()).getSessionById(testUuid)
    }

}
