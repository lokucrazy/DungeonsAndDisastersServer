package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.NPC
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class NPCRepositoryTests {

    @Autowired
    private NPCRepository npcRepository

    @Test
    void whenFindById_returnNPC() {
        //Given
        NPC npc = new NPC()
        npcRepository.save(npc)
        //When
        Optional<NPC> found = npcRepository.findById(npc.identifier)
        //Then
        Assert.assertEquals(found.get().id, npc.id)
    }
}
