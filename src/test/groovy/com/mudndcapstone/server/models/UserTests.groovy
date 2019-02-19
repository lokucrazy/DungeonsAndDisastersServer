package com.mudndcapstone.server.models

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class UserTests {

    @Test
    void givenEmptyUser_thenReturnAlmostEmptyUserObject() {
        // Given
        User user = new User()

        // Then
        Assert.assertNotNull(user.id)
        Assert.assertNull(user.characters)
        Assert.assertNull(user.sessions)
    }

    @Test
    void givenUser_whenAddProperties_thenUserObjectHasProperties() {
        // Given
        User user = new User()
        Set<Character> characters = [new Character()]
        Set<Session> sessions = [new Session()]

        // When
        user.setCharacters(characters)
        user.setSessions(sessions)

        // Then
        Assert.assertNotNull(user.id)
        Assert.assertEquals(user.characters, characters)
        Assert.assertEquals(user.sessions, sessions)
    }

}
