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
    void givenEmptyUser_thenReturnEmptyUserObject() {
        // Given
        User user = new User()

        // Then
        Assert.assertNull(user.id)
        Assert.assertNull(user.characters)
        Assert.assertNull(user.sessions)
    }

    @Test
    void givenUser_whenAddProperties_thenUserObjectHasProperties() {
        // Given
        User user = new User()
        String username = "testuser"
        String password = "password"
        Date birthdate = new Date()
        List<String> notes = [""]
        HashSet<Character> characters = [new Character()]
        HashSet<Session> sessions = [new Session()]
        HashSet<Session> dmSessions = [new Session()]
        HashSet<NPC> npcs = [new NPC()]

        // When
        user.setUsername(username)
        user.setPassword(password)
        user.setBirthdate(birthdate)
        user.setNotes(notes)
        user.setCharacters(characters)
        user.setSessions(sessions)
        user.setDmSessions(dmSessions)
        user.setNpcs(npcs)

        // Then
        Assert.assertNull(user.id)
        Assert.assertEquals(user.username, username)
        Assert.assertEquals(user.password, password)
        Assert.assertEquals(user.birthdate, birthdate)
        Assert.assertEquals(user.notes, notes)
        Assert.assertEquals(user.characters, characters)
        Assert.assertEquals(user.sessions, sessions)
        Assert.assertEquals(user.dmSessions, dmSessions)
        Assert.assertEquals(user.npcs, npcs)
    }

}
