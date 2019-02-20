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

    @Autowired
    private CharacterRepository characterRepository

    @Test
    void whenFindById_returnCharacter() {
        //Given
        Character character = new Character()
        characterRepository.save(character)
        //When
        Optional<Character> found = characterRepository.findById(character.id)
        //Then
        Assert.assertEquals(found.get().id,character.id)
    }
}
