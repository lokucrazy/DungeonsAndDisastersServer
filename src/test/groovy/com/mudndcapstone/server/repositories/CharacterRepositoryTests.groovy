package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Character
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class CharacterRepositoryTests {

    @Autowired CharacterRepository characterRepository

    @Test
    void givenCharacter_whenCharacterSavedToRepository_thenCharacterReturned() {
        // Given
        Character character = new Character()
        Character found

        // When
        characterRepository.save(character)
        found = characterRepository.findById(character.identifier).orElse(null)

        // Then
        Assert.assertNotNull(found)
        Assert.assertEquals(found.identifier, character.identifier)
    }

}
