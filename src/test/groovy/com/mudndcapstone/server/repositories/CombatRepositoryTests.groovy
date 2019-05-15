package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Combat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class CombatRepositoryTests {

    @Autowired CombatRepository combatRepository

    @Test
    void givenCombat_whenCombatSavedToRepository_thenCombatReturned() {
        // Given
        Combat combat = new Combat()
        Combat found

        // When
        combatRepository.save(combat)
        found = combatRepository.findById(combat.identifier).orElse(null)

        // Then
        assert found
        assert combat == found
    }

}
