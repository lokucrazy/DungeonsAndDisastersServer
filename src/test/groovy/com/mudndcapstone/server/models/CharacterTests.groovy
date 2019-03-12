package com.mudndcapstone.server.models

import com.mudndcapstone.server.utils.BeingAbilities
import com.mudndcapstone.server.utils.CharacterAlignment
import com.mudndcapstone.server.utils.CharacterClass
import com.mudndcapstone.server.utils.CharacterRace
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class CharacterTests {

    @Test
    void givenEmptyCharacter_thenReturnEmptyCharacterObject() {
        // Given
        Character character = new Character()

        // Then
        Assert.assertNull(character.id)
        Assert.assertNull(character.name)
        Assert.assertNull(character.user)
        Assert.assertEquals(character.level, 0)
    }

    @Test
    void givenCharacter_whenAddProperties_thenCharacterObjectsHasProperties() {
        // Given
        Character character = new Character()
        User user = new User()
        Set<Session> sessions = [new Session()]
        BeingAbilities abilities = new BeingAbilities()

        // When
        character.setName("King Sir the IV")
        character.setCharacterClass(CharacterClass.MONK)
        character.setLevel(1)
        character.setExperience(500)
        character.setBackground("King of Kingland")
        character.setRace(CharacterRace.DRAGONBORN)
        character.setAlignment(CharacterAlignment.NEUTRAL)
        abilities.setCharisma(100)
        character.setAbilities(abilities)
        character.setUser(user)
        character.setSessions(sessions)

        // Then
        Assert.assertNull(character.id)
        Assert.assertEquals(character.name, "King Sir the IV")
        Assert.assertEquals(character.characterClass, CharacterClass.MONK)
        Assert.assertEquals(character.level, 1)
        Assert.assertEquals(character.experience, 500)
        Assert.assertEquals(character.background, "King of Kingland")
        Assert.assertEquals(character.race, CharacterRace.DRAGONBORN)
        Assert.assertEquals(character.alignment, CharacterAlignment.NEUTRAL)
        Assert.assertEquals(character.abilities, abilities)
        Assert.assertEquals(character.abilities.charisma, 100)
        Assert.assertEquals(character.user, user)
        Assert.assertEquals(character.sessions, sessions)
    }

}
