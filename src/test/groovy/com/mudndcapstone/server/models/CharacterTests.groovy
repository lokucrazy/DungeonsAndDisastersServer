package com.mudndcapstone.server.models

import com.mudndcapstone.server.utils.BeingAbilities
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
    void givenEmptyCharacter_thenReturnAlmostEmptyCharacterObject() {
        // Given
        Character character = new Character()

        // Then
        Assert.assertNotNull(character.id)
        Assert.assertNull(character.name)
        Assert.assertNull(character.user)
        Assert.assertEquals(character.level, -1)
    }

    @Test
    void givenCharacter_whenAddProperties_thenCharacterObjectsHasProperties() {
        // Given
        Character character = new Character()
        User user = new User()
        List<Session> sessions = [new Session()]
        BeingAbilities abilities = new BeingAbilities()

        // When
        character.setName("King Sir the IV")
        character.setCharacterClass(CharacterClass.MONK)
        character.setLevel(1)
        character.setBackground("King of Kingland")
        character.setRace(CharacterRace.DRAGONBORN)
        abilities.setCharism(100)
        character.setAbilities(abilities)
        character.setUser(user)
        character.setSessions(sessions)

        // Then
        Assert.assertEquals(character.name, "King Sir the IV")
        Assert.assertEquals(character.characterClass, CharacterClass.MONK)
        Assert.assertEquals(character.level, 1)
        Assert.assertEquals(character.background, "King of Kingland")
        Assert.assertEquals(character.race, CharacterRace.DRAGONBORN)
        Assert.assertEquals(character.abilities, abilities)
        Assert.assertEquals(character.abilities.charism, 100)
        Assert.assertEquals(character.user, user)
        Assert.assertEquals(character.sessions, sessions)
    }

}
