package com.mudndcapstone.server.models

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class CombatTests {

    @Test
    void givenEmptyCombat_thenReturnEmptyCombatObject() {
        // Given
        Combat combat = new Combat()

        // Then
        Assert.assertNull(combat.id)
        Assert.assertNull(combat.previousCombat)
        Assert.assertNull(combat.session)
        Assert.assertNull(combat.enemies)
    }

    @Test
    void givenCombat_whenAddProperties_thenCombatObjectHasProperties() {
        // Given
        Combat combat = new Combat()
        Combat previousCombat = new Combat()
        Session session = new Session()
        List<Enemy> enemies = [new Enemy()]

        // When
        combat.setPreviousCombat(previousCombat)
        combat.setSession(session)
        combat.setEnemies(enemies)

        // Then
        Assert.assertNull(combat.id)
        Assert.assertEquals(combat.previousCombat, previousCombat)
        Assert.assertEquals(combat.session, session)
        Assert.assertEquals(combat.enemies, enemies)
    }

}
