package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.services.impl.CharacterServiceImpl
import org.junit.Assert
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
        List<Character> characters = [new Character()]
        List<CharacterDto> characterDtos = characterService.buildDtoListFrom(characters)

        // When
        Mockito.when(characterService.getAllCharacters()).thenReturn(characters)

        // Then
        ResponseEntity response = characterController.getAllCharacters()
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK)
        Assert.assertEquals(response.body, characterDtos)
        Mockito.verify(characterService, Mockito.atLeastOnce()).getAllCharacters()
    }

}
