package com.mudndcapstone.server.models.dto

import com.mudndcapstone.server.utils.BeingAbilities
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class NPCDtoTests {

    @Test
    void givenEmptyNPCDto_thenReturnEmptyNPCDtoObject() {
        // Given
        NPCDto npcDto = new NPCDto()

        // Then
        Assert.assertNull(npcDto.identifier)
        Assert.assertEquals(npcDto.health, 0)
        Assert.assertFalse(npcDto.isAlive)
        Assert.assertNull(npcDto.initialLocation)
        Assert.assertNull(npcDto.abilities)
        Assert.assertNull(npcDto.sessionId)
        Assert.assertNull(npcDto.dmId)
    }

    @Test
    void givenNPCDto_whenAddProperties_thenNPCDtoObjectHasProperties() {
        // Given
        NPCDto npcDto = new NPCDto()
        String initialLocation = "A1"
        BeingAbilities abilities = new BeingAbilities()

        // When
        npcDto.setHealth(100)
        npcDto.setIsAlive(true)
        npcDto.setInitialLocation(initialLocation)
        npcDto.setAbilities(abilities)
        npcDto.setSessionId(500)
        npcDto.setDmId(1000)

        // Then
        Assert.assertNull(npcDto.identifier)
        Assert.assertEquals(npcDto.health, 100)
        Assert.assertEquals(npcDto.isAlive, true)
        Assert.assertEquals(npcDto.initialLocation, initialLocation)
        Assert.assertEquals(npcDto.abilities, abilities)
        Assert.assertEquals(npcDto.sessionId, 500)
        Assert.assertEquals(npcDto.dmId, 1000)
    }

}
