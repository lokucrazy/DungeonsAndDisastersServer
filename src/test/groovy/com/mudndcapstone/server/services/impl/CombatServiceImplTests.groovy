package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.repositories.CombatRepository
import org.junit.Assert
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
class CombatServiceImplTests {

    @Mock
    private CombatRepository combatRepository

    @InjectMocks
    private CombatServiceImpl combatService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void whenGetCombatById_thenReturnCombat() {
        //Given
        Combat combat = new Combat()
        combatRepository.save(combat)
        //When
        Mockito.when(combatRepository.findById(combat.id).orElse(null)).thenReturn(Optional.of(combat))
        //Then
        Assert.assertEquals(combatService.getCombatById(combat.id), combat)
    }
}
