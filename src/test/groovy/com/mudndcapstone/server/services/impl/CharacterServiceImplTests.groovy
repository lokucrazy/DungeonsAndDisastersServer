package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.repositories.CharacterRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class CharacterServiceImplTests {

    @Mock
    private CharacterRepository characterRepository

    @InjectMocks
    private CharacterServiceImpl characterService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void whenGetCharacterById_returnCharacter() {
        //Given
        Character character = new Character()
        characterRepository.save(character)
        //When
        Mockito.when(characterRepository.findById(character.identifier).orElse(null)).thenReturn(Optional.of(character))
        //Then
        Assert.assertEquals(characterService.getCharacterById(character.identifier), character)
    }
}
