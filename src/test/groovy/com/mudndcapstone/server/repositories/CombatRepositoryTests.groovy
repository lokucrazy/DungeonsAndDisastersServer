package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Combat
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class CombatRepositoryTests {

    @Autowired
    private CombatRepository combatRepository

    @Test
    void whenFindById_returnCombat() {
        //Given
        Combat combat = new Combat()
        combatRepository.save(combat)
        //When
        Optional<Combat> found = combatRepository.findById(combat.identifier)
        //Then
        Assert.assertEquals(found.get().id, combat.id)
    }
}
