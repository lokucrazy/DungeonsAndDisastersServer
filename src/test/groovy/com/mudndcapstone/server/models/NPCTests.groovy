package com.mudndcapstone.server.models

import com.mudndcapstone.server.utils.BeingAbilities
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class NPCTests {

    @Test
    void givenEmptyNPC_thenReturnEmptyNPCObject() {
        // Given
        NPC npc = new NPC()

        // Then
        Assert.assertNull(npc.id)
        Assert.assertNull(npc.session)
        Assert.assertNull(npc.dm)
    }

    @Test
    void givenNPC_whenAddProperties_thenNPCObjectHasProperties() {
        // Given
        NPC npc = new NPC()
        int health = 100
        boolean isAlive = true
        String initialLocation = "A1"
        BeingAbilities abilities = new BeingAbilities()
        Session session = new Session()
        User dm = new User()

        // When
        npc.setHealth(health)
        npc.setIsAlive(isAlive)
        npc.setInitialLocation(initialLocation)
        npc.setAbilities(abilities)
        npc.setSession(session)
        npc.setDm(dm)

        // Then
        Assert.assertNull(npc.id)
        Assert.assertEquals(npc.health, health)
        Assert.assertEquals(npc.isAlive, isAlive)
        Assert.assertEquals(npc.initialLocation, initialLocation)
        Assert.assertEquals(npc.abilities, abilities)
        Assert.assertEquals(npc.session, session)
        Assert.assertEquals(npc.dm, dm)
    }

}
