package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.services.CharacterService
import com.mudndcapstone.server.services.SessionService
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
