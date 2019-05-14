package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.models.NPC
import com.mudndcapstone.server.models.dto.EnemyDto
import com.mudndcapstone.server.models.dto.NPCDto
import com.mudndcapstone.server.services.CombatService
import com.mudndcapstone.server.services.EnemyService
import com.mudndcapstone.server.services.NPCService
import com.mudndcapstone.server.services.UserService
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
class BeingControllerTests {

    @Mock NPCService npcService
    @Mock EnemyService enemyService
    @Mock CombatService combatService
    @Mock UserService userService

    @InjectMocks
    BeingController beingController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    // Enemies

    @Test
    void getResponseEntity_whenGivenEnemyId_forGetEnemyById() {
        // Mocks
        Mockito.when(enemyService.getEnemyById("test")).thenReturn(new Enemy())
        Mockito.when(enemyService.buildDtoFrom(Mockito.any(Enemy))).thenReturn(new EnemyDto())

        // Test
        ResponseEntity<EnemyDto> responseEntity = beingController.getEnemyById("test")

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.OK
        assert responseEntity.body != null
    }

    @Test
    void getResponseStatusException_whenGivenBadEnemyId_forGetEnemyById() {
        // Mocks
        Mockito.when(enemyService.getEnemyById("bad")).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            beingController.getEnemyById("bad")
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }
        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.NOT_FOUND
        assert responseStatusException.reason == Exceptions.ENEMY_NOT_FOUND_EXCEPTION
    }


    @Test
    void getUpdatedEnemyDto_whenGivenEnemyDto_forUpdateEnemy() {
        // Mocks
        Mockito.when(enemyService.existsByEnemyId("test")).thenReturn(true)
        Mockito.when(combatService.getCombatById("combat")).thenReturn(new Combat())
        Mockito.when(enemyService.buildEnemyFrom(Mockito.any(EnemyDto), Mockito.any(Combat))).thenReturn(new Enemy())
        Mockito.when(enemyService.upsertEnemy(Mockito.any(Enemy))).thenReturn(new Enemy())
        Mockito.when(enemyService.buildDtoFrom(Mockito.any(Enemy))).thenReturn(new EnemyDto())

        // Test
        ResponseEntity<EnemyDto> responseEntity = beingController.updateEnemy("test", new EnemyDto(combatId: "combat"))

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.OK
        assert responseEntity.body != null
    }

    @Test
    void getResponseStatusException_whenGivenBadEnemyId_forUpdateEnemy() {
        // Mocks
        Mockito.when(enemyService.existsByEnemyId("bad")).thenReturn(false)

        // Test
        ResponseStatusException responseStatusException
        try {
            beingController.updateEnemy("bad", new EnemyDto())
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.NOT_FOUND
        assert responseStatusException.reason == Exceptions.ENEMY_NOT_FOUND_EXCEPTION
    }

    @Test
    void getResponseStatusException_whenGivenBadCombatId_forUpdateEnemy() {
        // Mocks
        Mockito.when(enemyService.existsByEnemyId("test")).thenReturn(true)
        Mockito.when(combatService.getCombatById("bad")).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            beingController.updateEnemy("test", new EnemyDto())
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.BAD_REQUEST
        assert responseStatusException.reason == Exceptions.COMBAT_NOT_FOUND_EXCEPTION
    }

    @Test
    void getResponseStatusException_whenUpsertEnemyFails_forUpdateEnemy() {
        // Mocks
        Mockito.when(enemyService.existsByEnemyId("test")).thenReturn(true)
        Mockito.when(combatService.getCombatById("combat")).thenReturn(new Combat())
        Mockito.when(enemyService.buildEnemyFrom(Mockito.any(EnemyDto), Mockito.any(Combat))).thenReturn(new Enemy())
        Mockito.when(enemyService.upsertEnemy(Mockito.any(Enemy))).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            beingController.updateEnemy("test", new EnemyDto(combatId: "combat"))
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.INTERNAL_SERVER_ERROR
        assert responseStatusException.reason == Exceptions.ENEMY_NOT_UPDATED_EXCEPTION
    }

    @Test
    void getResponseEntity_whenGivenEnemyId_forDeleteEnemy() {
        // Mocks
        Mockito.doNothing().when(enemyService).deleteEnemy("test")

        //Test
        ResponseEntity responseEntity = beingController.deleteEnemy("test")

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.NO_CONTENT
    }

    @Test
    void getResponseEntity_whenGivenCombatId_forGetAllCombatsEnemies() {
        // Mocks
        Mockito.when(combatService.getCombatById("test")).thenReturn(new Combat(enemies: [new Enemy()]))
        Mockito.when(enemyService.buildDtoSetFrom(Mockito.any(Set))).thenReturn(new HashSet<EnemyDto>())

        // Test
        ResponseEntity<Set<EnemyDto>> responseEntity = beingController.getAllCombatsEnemies("test")

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.OK
        assert responseEntity.body != null
    }

    @Test
    void getResponseStatus_Exception_whenGivenBadCombatId_forGetAllCombatsEnemies() {
        // Mocks
        Mockito.when(combatService.getCombatById("bad")).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            beingController.getAllCombatsEnemies("bad")
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.NOT_FOUND
        assert responseStatusException.reason == Exceptions.COMBAT_NOT_FOUND_EXCEPTION
    }

    @Test
    void getResponseEntity_whenGivenCombatId_forCreateEnemy() {
        // Mocks
        Mockito.when(combatService.getCombatById("test")).thenReturn(new Combat())
        Mockito.when(enemyService.buildAndCreateEnemy(Mockito.any(EnemyDto), Mockito.any(Combat))).thenReturn(new Enemy())
        Mockito.when(enemyService.buildDtoFrom(Mockito.any(Enemy))).thenReturn(new EnemyDto())

        // Test
        ResponseEntity<EnemyDto> responseEntity = beingController.createEnemy("test", new EnemyDto())

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.OK
        assert responseEntity.body != null
    }

    @Test
    void getResponseStatusException_whenGivenBadCombatId_forCreateEnemy() {
        // Mocks
        Mockito.when(combatService.getCombatById("bad")).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            beingController.createEnemy("bad", null)
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.BAD_REQUEST
        assert responseStatusException.reason == Exceptions.COMBAT_NOT_FOUND_EXCEPTION
    }

    @Test
    void getResponseStatusException_whenBuildAndCreateEnemyFails_forCreateEnemy() {
        // Mocks
        Mockito.when(combatService.getCombatById("test")).thenReturn(new Combat())
        Mockito.when(enemyService.buildAndCreateEnemy(Mockito.any(EnemyDto), Mockito.any(Combat))).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            beingController.createEnemy("test", null)
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.INTERNAL_SERVER_ERROR
        assert responseStatusException.reason == Exceptions.ENEMY_NOT_CREATED_EXCEPTION
    }

    // NPCs

    @Test
    void getResponseEntity_whenGivenNPCId_forGetNPCById() {
        // Mocks
        Mockito.when(npcService.getNPCById("test")).thenReturn(new NPC())
        Mockito.when(npcService.buildDtoFrom(Mockito.any(NPC))).thenReturn(new NPCDto())

        // Test
        ResponseEntity<NPCDto> responseEntity = beingController.getNPCById("test")

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.OK
        assert responseEntity.body != null
    }

    @Test
    void getResponseStatusException_whenGivenBadNPCId_forGetNPCById() {
        // Mocks
        Mockito.when(npcService.getNPCById("bad")).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            beingController.getNPCById("bad")
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }
        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.NOT_FOUND
        assert responseStatusException.reason == Exceptions.NPC_NOT_FOUND_EXCEPTION
    }

    @Test
    void getUpdatedNPCDto_whenGivenNPCDto_forUpdateNPC() {
        // Mocks
        Mockito.when(npcService.existsByNPCId("test")).thenReturn(true)
        Mockito.when(userService.getDMById("dm")).thenReturn(new User())
        Mockito.when(npcService.buildNPCFrom(Mockito.any(NPCDto), Mockito.any(User))).thenReturn(new NPC())
        Mockito.when(npcService.upsertNPC(Mockito.any(NPC))).thenReturn(new NPC())
        Mockito.when(npcService.buildDtoFrom(Mockito.any(NPC))).thenReturn(new NPCDto())

        // Test
        ResponseEntity<NPCDto> responseEntity = beingController.updateNPC("test", new NPCDto(dmId: "dm"))

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.OK
        assert responseEntity.body != null
    }

    @Test
    void getResponseStatusException_whenGivenBadNPCId_forUpdateNPC() {
        // Mocks
        Mockito.when(npcService.existsByNPCId("bad")).thenReturn(false)

        // Test
        ResponseStatusException responseStatusException
        try {
            beingController.updateNPC("bad", null)
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.NOT_FOUND
        assert responseStatusException.reason == Exceptions.NPC_NOT_FOUND_EXCEPTION
    }

    @Test
    void getResponseStatusException_whenGivenBadDMId_forUpdateNPC() {
        // Mocks
        Mockito.when(npcService.existsByNPCId("test")).thenReturn(true)
        Mockito.when(userService.getDMById("bad")).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            beingController.updateNPC("test", new NPCDto(dmId: "bad"))
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.BAD_REQUEST
        assert responseStatusException.reason == Exceptions.USER_NOT_FOUND_EXCEPTION
    }

    @Test
    void getResponseStatusException_whenUpsertNPCFails_forUpdateNPC() {
        // Mocks
        Mockito.when(npcService.existsByNPCId("test")).thenReturn(true)
        Mockito.when(userService.getDMById("dm")).thenReturn(new User())
        Mockito.when(npcService.buildNPCFrom(Mockito.any(NPCDto), Mockito.any(User))).thenReturn(new NPC())
        Mockito.when(npcService.upsertNPC(Mockito.any(NPC))).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            beingController.updateNPC("test", new NPCDto(dmId: "dm"))
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.INTERNAL_SERVER_ERROR
        assert responseStatusException.reason == Exceptions.NPC_NOT_UPDATED_EXCEPTION
    }

    @Test
    void getResponseEntity_whenGivenNPCId_forDeleteNPC() {
        // Mocks
        Mockito.doNothing().when(enemyService).deleteEnemy("test")

        //Test
        ResponseEntity responseEntity = beingController.deleteEnemy("test")

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.NO_CONTENT
    }

    @Test
    void getResponseEntity_whenGivenDMId_forCreateNPC() {
        // Mocks
        Mockito.when(userService.getDMById("test")).thenReturn(new User())
        Mockito.when(npcService.buildAndCreateNPC(Mockito.any(NPCDto), Mockito.any(User))).thenReturn(new NPC())
        Mockito.when(npcService.buildDtoFrom(Mockito.any(NPC))).thenReturn(new NPCDto())

        // Test
        ResponseEntity<NPCDto> responseEntity = beingController.createNPC("test", new EnemyDto())

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.OK
        assert responseEntity.body != null
    }

    @Test
    void getResponseStatusException_whenGivenBadDMId_forCreateNPC() {
        // Mocks
        Mockito.when(userService.getDMById("bad")).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            beingController.createNPC("bad", null)
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.BAD_REQUEST
        assert responseStatusException.reason == Exceptions.USER_NOT_FOUND_EXCEPTION
    }

    @Test
    void getResponseStatusException_whenBuildAndCreateNPCFails_forCreateNPC() {
        // Mocks
        Mockito.when(userService.getDMById("test")).thenReturn(new User())
        Mockito.when(npcService.buildAndCreateNPC(Mockito.any(NPCDto), Mockito.any(User))).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            beingController.createNPC("test", null)
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.INTERNAL_SERVER_ERROR
        assert responseStatusException.reason == Exceptions.NPC_NOT_CREATED_EXCEPTION
    }
}
