package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.models.NPC
import com.mudndcapstone.server.models.dto.EnemyDto
import com.mudndcapstone.server.models.dto.NPCDto
import com.mudndcapstone.server.services.EnemyService
import com.mudndcapstone.server.services.NPCService
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
    void initTest() {
        assert 1 == 1
    }

}
