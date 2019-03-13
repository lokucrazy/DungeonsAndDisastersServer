package com.mudndcapstone.server.models.dto

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
class CharacterDtoTests {

    @Test
    void givenEmptyCharacterDto_thenReturnEmptyCharacterDtoObject() {
        // Given
        CharacterDto characterDto = new CharacterDto()

        // Then
        assert !characterDto.identifier
        assert !characterDto.userId
        assert !characterDto.characterClass
        assert !characterDto.background
        assert !characterDto.race
        assert !characterDto.alignment
        assert characterDto.level == 0
        assert characterDto.experience == 0
        assert !characterDto.abilities
        assert !characterDto.sessionIds
    }

    @Test
    void givenCharacterDto_whenAddProperties_thenCharacterDtoObjectsHasProperties() {
        // Given
        String testUuid = UUID.randomUUID().toString()
        CharacterDto characterDto = new CharacterDto()
        Set<String> sessionIds = [testUuid]
        BeingAbilities abilities = new BeingAbilities()

        // When
        characterDto.setName("King Sir the IV")
        characterDto.setCharacterClass(CharacterClass.MONK)
        characterDto.setLevel(1)
        characterDto.setExperience(500)
        characterDto.setBackground("King of Kingland")
        characterDto.setRace(CharacterRace.DRAGONBORN)
        characterDto.setAlignment(CharacterAlignment.NEUTRAL)
        abilities.setCharisma(100)
        characterDto.setAbilities(abilities)
        characterDto.setUserId(testUuid)
        characterDto.setSessionIds(sessionIds)

        // Then
        assert !characterDto.identifier
        assert characterDto.name == "King Sir the IV"
        assert characterDto.characterClass == CharacterClass.MONK
        assert characterDto.level == 1
        assert characterDto.experience == 500
        assert characterDto.background == "King of Kingland"
        assert characterDto.race == CharacterRace.DRAGONBORN
        assert characterDto.alignment == CharacterAlignment.NEUTRAL
        assert characterDto.abilities == abilities
        assert characterDto.abilities.charisma == 100
        assert characterDto.userId == testUuid
        assert characterDto.sessionIds == sessionIds
    }

}
