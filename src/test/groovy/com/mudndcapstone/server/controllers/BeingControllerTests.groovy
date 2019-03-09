package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.models.NPC
import com.mudndcapstone.server.services.EnemyService
import com.mudndcapstone.server.services.NPCService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class BeingControllerTests {

    @Mock NPCService npcService
    @Mock EnemyService enemyService

    @InjectMocks
    BeingController beingController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenNPCList_whenNPCServiceReturnsList_thenNPCControllerReturnsList() {
        // Given
        List<NPC> npcs = [new NPC()]

        // When
        Mockito.when(npcService.getAllNPCs()).thenReturn(npcs.asList())

        // Then
        ResponseEntity response = beingController.getAllNPCs()
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK)
        Assert.assertEquals(response.body, npcs)
        Mockito.verify(npcService, Mockito.atLeastOnce()).getAllNPCs()
    }

    @Test
    void givenEnemyList_whenEnemyServiceReturnsList_thenEnemyControllerReturnsList() {
        // Given
        List<Enemy> enemies = [new Enemy()]

        // When
        Mockito.when(enemyService.getAllEnemies()).thenReturn(enemies.asList())

        // Then
        ResponseEntity response = beingController.getAllEnemies()
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK)
        Assert.assertEquals(response.body, enemies)
        Mockito.verify(enemyService, Mockito.atLeastOnce()).getAllEnemies()
    }

}
