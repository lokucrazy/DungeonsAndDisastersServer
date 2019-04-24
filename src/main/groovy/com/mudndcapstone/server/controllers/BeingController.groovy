package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.models.NPC
import com.mudndcapstone.server.models.dto.EnemyDto
import com.mudndcapstone.server.models.dto.NPCDto
import com.mudndcapstone.server.services.EnemyService
import com.mudndcapstone.server.services.NPCService
import com.mudndcapstone.server.utils.Exceptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

@RestController
class BeingController {

    @Autowired NPCService npcService
    @Autowired EnemyService enemyService

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

    @PostMapping("/combats/{combatId}/enemies")
    ResponseEntity<EnemyDto> createEnemy(@Valid @RequestBody EnemyDto enemyDto) {
        Enemy enemyRequest = enemyService.buildEnemyFrom(enemyDto)
        Enemy enemy = enemyService.createEnemy(enemyRequest)
        if (!enemy) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.ENEMY_NOT_CREATED_EXCEPTION)

        EnemyDto created = enemyService.buildDtoFrom(enemy)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

    /* NPCs */
    @PostMapping("/dms/{dmId}/npcs")
    ResponseEntity<NPCDto> createNPC(@Valid @RequestBody NPCDto npcDto) {
        NPC npcRequest = npcService.buildNPCFrom(npcDto)
        NPC npc = npcService.createNPC(npcRequest)
        if (!npc) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.NPC_NOT_CREATED_EXCEPTION)

        NPCDto created = npcService.buildDtoFrom(npc)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

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

}
