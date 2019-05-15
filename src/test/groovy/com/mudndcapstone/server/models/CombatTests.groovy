package com.mudndcapstone.server.models

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
        assert !combat.identifier
        assert !combat.nextCombat
        assert !combat.session
        assert !combat.enemies
    }

    @Test
    void givenCombat_whenAddProperties_thenCombatObjectHasProperties() {
        // Given
        Combat combat = new Combat()
        Combat previousCombat = new Combat()
        Session session = new Session()
        Set<Enemy> enemies = [new Enemy()]

        // When
        combat.setNextCombat(previousCombat)
        combat.setSession(session)
        combat.setEnemies(enemies)

        // Then
        assert !combat.identifier
        assert combat.nextCombat == previousCombat
        assert combat.session == session
        assert combat.enemies == enemies
    }

}
