package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.models.NPC
import com.mudndcapstone.server.models.dto.EnemyDto
import com.mudndcapstone.server.models.dto.NPCDto
import com.mudndcapstone.server.services.impl.EnemyServiceImpl
import com.mudndcapstone.server.services.impl.NPCServiceImpl
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

    @Mock NPCServiceImpl npcService
    @Mock EnemyServiceImpl enemyService

    @InjectMocks
    BeingController beingController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenEnemyList_whenEnemyServiceReturnsList_thenEnemyControllerReturnsList() {
        // Given
        Set<Enemy> enemies = [new Enemy()]
        Set<EnemyDto> enemyDtos = enemyService.buildDtoSetFrom(enemies)
        ResponseEntity response

        // When
        Mockito.when(enemyService.getAllEnemies()).thenReturn(enemies)
        response = beingController.getAllEnemies()

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == enemyDtos
        Mockito.verify(enemyService, Mockito.atLeastOnce()).getAllEnemies()
    }

    @Test
    void givenNPCList_whenNPCServiceReturnsList_thenNPCControllerReturnsList() {
        // Given
        Set<NPC> npcs = [new NPC()]
        Set<NPCDto> npcDtos = npcService.buildDtoSetFrom(npcs)
        ResponseEntity response

        // When
        Mockito.when(npcService.getAllNPCs()).thenReturn(npcs)
        response = beingController.getAllNPCs()

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == npcDtos
        Mockito.verify(npcService, Mockito.atLeastOnce()).getAllNPCs()
    }

}
