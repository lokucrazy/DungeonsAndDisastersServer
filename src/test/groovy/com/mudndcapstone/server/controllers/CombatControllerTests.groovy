package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.State
import com.mudndcapstone.server.models.dto.CombatDto
import com.mudndcapstone.server.services.CombatService
import com.mudndcapstone.server.services.SessionService
import com.mudndcapstone.server.utils.Exceptions
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
import org.springframework.web.server.ResponseStatusException

@RunWith(SpringRunner)
@SpringBootTest
class CombatControllerTests {

    @Mock CombatService combatService
    @Mock SessionService sessionService

    @InjectMocks
    CombatController combatController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void getResponseEntity_whenGivenCombatId_forGetCombatById() {
        // Mocks
        Mockito.when(combatService.getCombatById("test")).thenReturn(new Combat())
        Mockito.when(combatService.buildDtoFrom(Mockito.any(Combat))).thenReturn(new CombatDto())

        // Test
        ResponseEntity<CombatDto> responseEntity = combatController.getCombatById("test")

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.OK
        assert responseEntity.body != null
    }

    @Test
    void getResponseStatusException_whenGivenBadCombatId_forGetCombatById() {
        // Mocks
        Mockito.when(combatService.getCombatById("bad")).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            combatController.getCombatById("bad")
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.NOT_FOUND
        assert responseStatusException.reason == Exceptions.COMBAT_NOT_FOUND_EXCEPTION
    }

    @Test
    void getResponseEntity_whenGivenSessionId_forSetCombatState() {
        // Mocks
        Mockito.when(sessionService.getSessionById("test")).thenReturn(new Session(combat: new Combat()))
        Mockito.when(combatService.setCombatState(Mockito.any(Combat), Mockito.any(State))).thenReturn(new Combat())
        Mockito.when(combatService.buildDtoFrom(Mockito.any(Combat))).thenReturn(new CombatDto())

        // Test
        ResponseEntity<CombatDto> responseEntity = combatController.setCombatState("test", new State())

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.OK
        assert responseEntity.body != null
    }
}
