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
import org.neo4j.ogm.session.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class CharacterServiceImplTests {

    @Mock CharacterRepository characterRepository

    @InjectMocks
    CharacterServiceImpl characterService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenCharacter_whenCharacterRepositorySavesCharacter_thenCharacterServiceReturnsCharacter() {
        // Given
        Character character = new Character()

        // When
        characterRepository.save(character)
        Mockito.when(characterRepository.findById(character.identifier)).thenReturn(Optional.of(character))

        // Then
        Assert.assertEquals(characterService.getCharacterById(character.identifier), character)
    }

}
