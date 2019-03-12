package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.dto.CombatDto
import com.mudndcapstone.server.services.impl.CombatServiceImpl
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
class CombatControllerTests {

    @Mock CombatServiceImpl combatService

    @InjectMocks
    CombatController combatController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenCombatList_whenCombatServiceReturnsList_thenCombatControllerReturnsList() {
        // Given
        Set<Combat> combats = [new Combat()]
        Set<CombatDto> combatDtos = combatService.buildDtoSetFrom(combats)

        // When
        Mockito.when(combatService.getAllCombats()).thenReturn(combats)

        // Then
        ResponseEntity response = combatController.getAllCombats()
        Assert.assertEquals(response.statusCode, HttpStatus.OK)
        Assert.assertEquals(response.body, combatDtos)
        Mockito.verify(combatService, Mockito.atLeastOnce()).getAllCombats()
    }

}
