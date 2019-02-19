package com.mudndcapstone.server.models

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class NPCTests {

    @Test
    void givenEmptyNPC_thenReturnAlmostEmptyNPCObject() {
        // Given
        NPC npc = new NPC()

        // Then
        Assert.assertNotNull(npc.id)
        Assert.assertNull(npc.session)
        Assert.assertNull(npc.dm)
    }

    @Test
    void givenNPC_whenAddProperties_thenNPCObjectHasProperties() {
        // Given
        NPC npc = new NPC()
        Session session = new Session()
        DM dm = new DM()

        // When
        npc.setSession(session)
        npc.setDm(dm)

        // Then
        Assert.assertNotNull(npc.id)
        Assert.assertEquals(npc.session, session)
        Assert.assertEquals(npc.dm, dm)
    }
}
