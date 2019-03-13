package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.repositories.CharacterRepository
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
class CharacterServiceTests {

    @Mock CharacterRepository characterRepository

    @InjectMocks
    CharacterService characterService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenCharacter_whenCharacterRepositorySavesCharacter_thenCharacterServiceReturnsCharacter() {
        // Given
        Character character = new Character()
        Character found

        // When
        characterRepository.save(character)
        Mockito.when(characterRepository.findById(character.identifier)).thenReturn(Optional.of(character))
        found = characterService.getCharacterById(character.identifier)

        // Then
        assert character == found
    }

}
