package com.mudndcapstone.server.models

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class DMTests {

    @Test
    void givenEmptyDM_thenReturnAlmostEmptyDMObject() {
        // Given
        DM dm = new DM()

        // Then
        Assert.assertNull(dm.id)
        Assert.assertNull(dm.sessions)
        Assert.assertNull(dm.npcs)
    }

    @Test
    void givenDM_whenAddProperties_thenDMObjectHasProperties() {
        // Given
        DM dm = new DM()
        Set<Session> dmSessions = [new Session()]
        Set<NPC> npcs = [new NPC()]

        // When
        dm.setDmSessions(dmSessions)
        dm.setNpcs(npcs)

        // Then
        Assert.assertNull(dm.id)
        Assert.assertEquals(dm.dmSessions, dmSessions)
        Assert.assertEquals(dm.npcs, npcs)
    }

}
