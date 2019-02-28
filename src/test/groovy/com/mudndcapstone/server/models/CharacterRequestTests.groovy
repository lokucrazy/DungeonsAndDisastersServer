package com.mudndcapstone.server.models

import com.mudndcapstone.server.utils.CharacterClass
import com.mudndcapstone.server.utils.CharacterRace
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class CharacterRequestTests {

    @Test
    void givenEmptyCharacter_thenReturnEmptyCharacterObject() {
        // Given
        CharacterRequest character = new Character()

        // Then
        Assert.assertNull(character.id)
        Assert.assertNull(character.name)
    }

    @Test
    void givenCharacter_whenAddProperties_thenCharacterObjectsHasProperties() {
        // Given
        CharacterRequest character = new Character()

        // When
        character.setName("King Sir the IV")
        character.setCharacterClass(CharacterClass.MONK)
        character.setBackground("King of Kingland")
        character.setRace(CharacterRace.DRAGONBORN)

        // Then
        Assert.assertNull(character.id)
        Assert.assertEquals(character.name, "King Sir the IV")
        Assert.assertEquals(character.characterClass, CharacterClass.MONK)
        Assert.assertEquals(character.background, "King of Kingland")
        Assert.assertEquals(character.race, CharacterRace.DRAGONBORN)
    }

}
