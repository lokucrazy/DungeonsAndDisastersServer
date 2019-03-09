package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.services.CharacterService
import com.mudndcapstone.server.services.EnemyService
import com.mudndcapstone.server.services.NPCService
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
class BeingControllerTests {

    @Mock
    CharacterService characterService

    @Mock
    NPCService npcService

    @Mock
    EnemyService enemyService

    @InjectMocks
    BeingController beingController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void whenGetAllCharacters_returnCharacterList() {
        //Given
        List<Character> characters = new ArrayList<Character>()
        Mockito.when(characterService.getAllCharacters()).thenReturn(characters.asList())
        //Then
        ResponseEntity response = beingController.getAllCharacters()
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK)
        Assert.assertEquals(response.body, characters)
        Mockito.verify(characterService, Mockito.atLeastOnce()).getAllCharacters()
    }
}
