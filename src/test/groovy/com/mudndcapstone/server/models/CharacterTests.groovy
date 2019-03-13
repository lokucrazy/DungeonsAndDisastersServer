package com.mudndcapstone.server.models

import com.mudndcapstone.server.utils.BeingAbilities
import com.mudndcapstone.server.utils.CharacterAlignment
import com.mudndcapstone.server.utils.CharacterClass
import com.mudndcapstone.server.utils.CharacterRace
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
        assert !character.identifier
        assert !character.name
        assert !character.user
        assert character.level == 0
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
        assert !character.identifier
        assert character.name == "King Sir the IV"
        assert character.characterClass == CharacterClass.MONK
        assert character.level == 1
        assert character.experience == 500
        assert character.background == "King of Kingland"
        assert character.race == CharacterRace.DRAGONBORN
        assert character.alignment == CharacterAlignment.NEUTRAL
        assert character.abilities == abilities
        assert character.abilities.charisma == 100
        assert character.user == user
        assert character.sessions == sessions
    }

}
