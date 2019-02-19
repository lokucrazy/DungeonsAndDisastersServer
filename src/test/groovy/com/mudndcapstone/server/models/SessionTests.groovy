package com.mudndcapstone.server.models

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class SessionTests {

    @Test
    void givenEmptySession_thenReturnAlmostEmptySessionObject() {
        // Given
        Session session = new Session()

        // Then
        Assert.assertNotNull(session.id)
        Assert.assertNull(session.chatLog)
        Assert.assertNull(session.mapList)
        Assert.assertNull(session.combatList)
        Assert.assertNull(session.dm)
        Assert.assertNull(session.npcs)
        Assert.assertNull(session.players)
        Assert.assertNull(session.characters)
    }

    @Test
    void givenSession_whenAddProperties_thenSessionObjectHasProperties() {
        // Given
        Session session = new Session()
        Chat chatLog = new Chat()
        Map mapList = new Map()
        Combat combatList = new Combat()
        DM dm = new DM()
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
        Assert.assertNotNull(session.id)
        Assert.assertEquals(session.chatLog, chatLog)
        Assert.assertEquals(session.mapList, mapList)
        Assert.assertEquals(session.combatList, combatList)
        Assert.assertEquals(session.dm, dm)
        Assert.assertEquals(session.npcs, npcs)
        Assert.assertEquals(session.players, players)
        Assert.assertEquals(session.characters, characters)
    }

}