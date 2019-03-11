package com.mudndcapstone.server.models.dto

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
class CharacterDtoTests {

    @Test
    void givenEmptyCharacterDto_thenReturnEmptyCharacterDtoObject() {
        // Given
        CharacterDto characterDto = new CharacterDto()

        // Then
        Assert.assertNull(characterDto.identifier)
        Assert.assertNull(characterDto.userId)
        Assert.assertNull(characterDto.characterClass)
        Assert.assertNull(characterDto.background)
        Assert.assertNull(characterDto.race)
        Assert.assertNull(characterDto.alignment)
        Assert.assertEquals(characterDto.level, 0)
        Assert.assertEquals(characterDto.experience, 0)
        Assert.assertNull(characterDto.abilities)
        Assert.assertNull(characterDto.sessionIds)
    }

    @Test
    void givenCharacterDto_whenAddProperties_thenCharacterDtoObjectsHasProperties() {
        // Given
        String testUuid = UUID.randomUUID().toString()
        CharacterDto characterDto = new CharacterDto()
        List<String> sessionIds = [testUuid]
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
        Assert.assertNull(characterDto.identifier)
        Assert.assertEquals(characterDto.name, "King Sir the IV")
        Assert.assertEquals(characterDto.characterClass, CharacterClass.MONK)
        Assert.assertEquals(characterDto.level, 1)
        Assert.assertEquals(characterDto.experience, 500)
        Assert.assertEquals(characterDto.background, "King of Kingland")
        Assert.assertEquals(characterDto.race, CharacterRace.DRAGONBORN)
        Assert.assertEquals(characterDto.alignment, CharacterAlignment.NEUTRAL)
        Assert.assertEquals(characterDto.abilities, abilities)
        Assert.assertEquals(characterDto.abilities.charisma, 100)
        Assert.assertEquals(characterDto.userId, testUuid)
        Assert.assertEquals(characterDto.sessionIds, sessionIds)
    }

}
