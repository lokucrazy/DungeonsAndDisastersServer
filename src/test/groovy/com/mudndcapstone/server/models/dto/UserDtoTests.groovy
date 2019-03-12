package com.mudndcapstone.server.models.dto

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class UserDtoTests {

    @Test
    void givenEmptyUserDto_thenReturnEmptyUserDtoObject() {
        // Given
        UserDto userDto = new UserDto()

        // Then
        Assert.assertNull(userDto.identifier)
        Assert.assertNull(userDto.username)
        Assert.assertNull(userDto.password)
        Assert.assertNull(userDto.birthdate)
        Assert.assertNull(userDto.notes)
        Assert.assertNull(userDto.characterIds)
        Assert.assertNull(userDto.sessionIds)
        Assert.assertNull(userDto.dmSessionIds)
        Assert.assertNull(userDto.npcIds)
    }

    @Test
    void givenUserDto_whenAddProperties_thenUserDtoObjectHasProperties() {
        // Given
        UserDto userDto = new UserDto()
        Date birthdate = new Date()
        List<String> notes = ["n test"]
        HashSet<Long> characterIds = [100]
        Set<Long> sessionIds = [300]
        Set<Long> dmSessionIds = [500]
        Set<Long> npcIds = [700]

        // When
        userDto.setUsername("test")
        userDto.setPassword("test")
        userDto.setBirthdate(birthdate)
        userDto.setNotes(notes)
        userDto.setCharacterIds(characterIds)
        userDto.setSessionIds(sessionIds)
        userDto.setDmSessionIds(dmSessionIds)
        userDto.setNpcIds(npcIds)

        // Then
        Assert.assertNull(userDto.identifier)
        Assert.assertEquals(userDto.username, "test")
        Assert.assertEquals(userDto.password, "test")
        Assert.assertEquals(userDto.birthdate, birthdate)
        Assert.assertEquals(userDto.notes, notes)
        Assert.assertEquals(userDto.characterIds, characterIds)
        Assert.assertEquals(userDto.sessionIds, sessionIds)
        Assert.assertEquals(userDto.dmSessionIds, dmSessionIds)
        Assert.assertEquals(userDto.npcIds, npcIds)
    }

}
