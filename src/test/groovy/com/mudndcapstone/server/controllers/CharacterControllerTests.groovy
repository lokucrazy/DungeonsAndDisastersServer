package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.services.impl.CharacterServiceImpl
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

    @Mock CharacterServiceImpl characterService

    @InjectMocks
    CharacterController characterController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenCharacterList_whenCharacterServiceReturnsList_thenCharacterControllerReturnsList() {
        // Given
        Set<Character> characters = [new Character()]
        Set<CharacterDto> characterDtos = characterService.buildDtoSetFrom(characters)
        ResponseEntity response

        // When
        Mockito.when(characterService.getAllCharacters()).thenReturn(characters)
        response = characterController.getAllCharacters()

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == characterDtos
        Mockito.verify(characterService, Mockito.atLeastOnce()).getAllCharacters()
    }

}
