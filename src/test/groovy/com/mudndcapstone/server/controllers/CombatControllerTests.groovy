package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.dto.CombatDto
import com.mudndcapstone.server.services.CombatService
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

    @Mock CombatService combatService

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
        ResponseEntity response

        // When
        Mockito.when(combatService.getAllCombats()).thenReturn(combats)
        response = combatController.getAllCombats()

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == combatDtos
        Mockito.verify(combatService, Mockito.atLeastOnce()).getAllCombats()
    }

}
