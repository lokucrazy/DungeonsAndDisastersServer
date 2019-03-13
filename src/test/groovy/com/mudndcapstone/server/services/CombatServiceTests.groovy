package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.repositories.CombatRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class CombatServiceTests {

    @Mock CombatRepository combatRepository

    @InjectMocks
    CombatService combatService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenCombat_whenCombatRepositorySavesCombat_thenCombatServiceReturnsCombat() {
        // Given
        Combat combat = new Combat()
        Combat found

        // When
        combatRepository.save(combat)
        Mockito.when(combatRepository.findById(combat.identifier)).thenReturn(Optional.of(combat))
        found = combatService.getCombatById(combat.identifier)

        // Then
        assert combat == found
    }

}
