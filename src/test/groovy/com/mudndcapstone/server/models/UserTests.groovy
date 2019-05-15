package com.mudndcapstone.server.models

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
        assert !user.identifier
        assert !user.characters
        assert !user.sessions
    }

    @Test
    void givenUser_whenAddProperties_thenUserObjectHasProperties() {
        // Given
        User user = new User()
        String username = "testuser"
        String password = "password"
        Date birthdate = new Date()
        List<String> notes = [""]
        Set<Character> characters = [new Character()]
        Set<Session> sessions = [new Session()]
        Set<Session> dmSessions = [new Session()]
        Set<NPC> npcs = [new NPC()]

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
        assert !user.identifier
        assert user.username == username
        assert user.password == password
        assert user.birthdate == birthdate
        assert user.notes == notes
        assert user.characters == characters
        assert user.sessions == sessions
        assert user.dmSessions == dmSessions
        assert user.npcs == npcs
    }

}
