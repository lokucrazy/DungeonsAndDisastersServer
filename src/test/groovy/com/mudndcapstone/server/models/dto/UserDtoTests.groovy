package com.mudndcapstone.server.models.dto

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
        assert !userDto.identifier
        assert !userDto.username
        assert !userDto.password
        assert !userDto.birthdate
        assert !userDto.notes
        assert !userDto.characterIds
        assert !userDto.sessionIds
        assert !userDto.dmSessionIds
        assert !userDto.npcIds
    }

    @Test
    void givenUserDto_whenAddProperties_thenUserDtoObjectHasProperties() {
        // Given
        UserDto userDto = new UserDto()
        Date birthdate = new Date()
        List<String> notes = ["n test"]
        Set<String> characterIds = [UUID.randomUUID().toString()]
        Set<String> sessionIds = [UUID.randomUUID().toString()]
        Set<String> dmSessionIds = [UUID.randomUUID().toString()]
        Set<String> npcIds = [UUID.randomUUID().toString()]

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
        assert !userDto.identifier
        assert userDto.username == "test"
        assert userDto.password == "test"
        assert userDto.birthdate == birthdate
        assert userDto.notes == notes
        assert userDto.characterIds == characterIds
        assert userDto.sessionIds == sessionIds
        assert userDto.dmSessionIds == dmSessionIds
        assert userDto.npcIds == npcIds
    }

}
