package com.mudndcapstone.server.models.dto

import com.mudndcapstone.server.utils.BeingAbilities
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
        assert !npcDto.identifier
        assert npcDto.health == 0
        assert !npcDto.isAlive
        assert !npcDto.initialLocation
        assert !npcDto.abilities
        assert !npcDto.sessionId
        assert !npcDto.dmId
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
        assert !npcDto.identifier
        assert npcDto.health == 100
        assert npcDto.isAlive
        assert npcDto.initialLocation == initialLocation
        assert npcDto.abilities == abilities
        assert npcDto.sessionId == 500
        assert npcDto.dmId == 1000
    }

}
