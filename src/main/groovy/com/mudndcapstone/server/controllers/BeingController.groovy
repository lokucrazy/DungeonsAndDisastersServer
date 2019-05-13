package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.models.NPC
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.EnemyDto
import com.mudndcapstone.server.models.dto.NPCDto
import com.mudndcapstone.server.services.CombatService
import com.mudndcapstone.server.services.EnemyService
import com.mudndcapstone.server.services.NPCService
import com.mudndcapstone.server.services.UserService
import com.mudndcapstone.server.utils.Exceptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

@RestController
@CrossOrigin("*")
class BeingController {

    @Autowired NPCService npcService
    @Autowired EnemyService enemyService
    @Autowired CombatService combatService
    @Autowired UserService userService

    /* Enemies */
    @GetMapping("/enemies/{enemyId}")
    ResponseEntity<EnemyDto> getEnemyById(@PathVariable String enemyId) {
        Enemy enemy = enemyService.getEnemyById(enemyId)
        if (!enemy) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.ENEMY_NOT_FOUND_EXCEPTION)

        EnemyDto enemyDto = enemyService.buildDtoFrom(enemy)
        new ResponseEntity<>(enemyDto, HttpStatus.OK)
    }

    @PutMapping("/enemies/{enemyId}")
    ResponseEntity<EnemyDto> updateEnemy(@PathVariable String enemyId, @Valid @RequestBody EnemyDto enemyDto) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, Exceptions.ROUTE_NOT_IMPLEMENTED)
    }

    @DeleteMapping("/enemies/{enemyId}")
    ResponseEntity deleteEnemy(@PathVariable String enemyId) {
        enemyService.deleteEnemy(enemyId)
        new ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("combats/{combatId}/enemies")
    ResponseEntity<Set<EnemyDto>> getAllCombatsEnemies(@PathVariable String combatId) {
        Combat combat = combatService.getCombatById(combatId)
        if (!combat) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.COMBAT_NOT_FOUND_EXCEPTION)

        Set<EnemyDto> enemyDtos = enemyService.buildDtoSetFrom(combat.enemies)
        new ResponseEntity<>(enemyDtos, HttpStatus.OK)
    }

    @PostMapping("/combats/{combatId}/enemies")
    ResponseEntity<EnemyDto> createEnemy(@PathVariable String combatId, @Valid @RequestBody EnemyDto enemyDto) {
        Combat combat = combatService.getCombatById(combatId)
        if (!combat) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.COMBAT_NOT_FOUND_EXCEPTION)

        Enemy enemy = enemyService.buildAndCreateEnemy(enemyDto, combat)
        if (!enemy) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.ENEMY_NOT_CREATED_EXCEPTION)

        EnemyDto created = enemyService.buildDtoFrom(enemy)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

    /* NPCs */
    @GetMapping("/npcs/{npcId}")
    ResponseEntity<NPCDto> getNPCById(@PathVariable String npcId) {
        NPC npc = npcService.getNPCById(npcId)
        if (!npc) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.NPC_NOT_FOUND_EXCEPTION)

        NPCDto npcDto = npcService.buildDtoFrom(npc)
        new ResponseEntity<>(npcDto, HttpStatus.OK)
    }

    @PutMapping("/npcs/{npcId}")
    ResponseEntity<NPCDto> updateNPC(@PathVariable String npcId, @Valid @RequestBody NPCDto npcDto) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, Exceptions.ROUTE_NOT_IMPLEMENTED)
    }

    @DeleteMapping("/npcs/{npcId}")
    ResponseEntity deleteNPC(@PathVariable String npcId) {
        npcService.deleteNPC(npcId)
        new ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PostMapping("/dms/{dmId}/npcs")
    ResponseEntity<NPCDto> createNPC(@PathVariable String dmId, @Valid @RequestBody NPCDto npcDto) {
        User dm = userService.getDMById(dmId)
        if (!dm) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.USER_NOT_FOUND_EXCEPTION)
      
        NPC npc = npcService.buildAndCreateNPC(npcDto, dm)
        if (!npc) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.NPC_NOT_CREATED_EXCEPTION)

        NPCDto created = npcService.buildDtoFrom(npc)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

}
