package com.mudndcapstone.server.models

import com.mudndcapstone.server.utils.BeingAbilities
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
        assert !npc.identifier
        assert !npc.session
        assert !npc.dm
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
        assert !npc.identifier
        assert npc.health == health
        assert npc.isAlive == isAlive
        assert npc.initialLocation == initialLocation
        assert npc.abilities == abilities
        assert npc.session == session
        assert npc.dm == dm
    }

}
