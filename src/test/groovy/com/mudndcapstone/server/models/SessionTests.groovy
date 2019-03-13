package com.mudndcapstone.server.models

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class SessionTests {

    @Test
    void givenEmptySession_thenReturnEmptySessionObject() {
        // Given
        Session session = new Session()

        // Then
        assert !session.identifier
        assert !session.chatLog
        assert !session.mapList
        assert !session.combatList
        assert !session.dm
        assert !session.npcs
        assert !session.players
        assert !session.characters
    }

    @Test
    void givenSession_whenAddProperties_thenSessionObjectHasProperties() {
        // Given
        Session session = new Session()
        Chat chatLog = new Chat()
        Map mapList = new Map()
        Combat combatList = new Combat()
        User dm = new User()
        Set<NPC> npcs = [new NPC()]
        Set<User> players = [new User()]
        Set<Character> characters = [new Character()]

        // When
        session.setChatLog(chatLog)
        session.setMapList(mapList)
        session.setCombatList(combatList)
        session.setDm(dm)
        session.setNpcs(npcs)
        session.setPlayers(players)
        session.setCharacters(characters)

        // Then
        assert !session.identifier
        assert session.chatLog == chatLog
        assert session.mapList == mapList
        assert session.combatList == combatList
        assert session.dm == dm
        assert session.npcs == npcs
        assert session.players == players
        assert session.characters == characters
    }

}
