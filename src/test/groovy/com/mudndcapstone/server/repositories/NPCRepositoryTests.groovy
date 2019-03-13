package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.NPC
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class NPCRepositoryTests {

    @Autowired NPCRepository npcRepository

    @Test
    void givenNPC_whenNPCSavedToRepository_thenNPCReturned() {
        // Given
        NPC npc = new NPC()
        NPC found

        // When
        npcRepository.save(npc)
        found = npcRepository.findById(npc.identifier).orElse(null)

        // Then
        assert found
        assert npc == found
    }

}
