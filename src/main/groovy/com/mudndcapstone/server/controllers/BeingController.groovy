package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.models.NPC
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.EnemyDto
import com.mudndcapstone.server.models.dto.NPCDto
import com.mudndcapstone.server.services.EnemyService
import com.mudndcapstone.server.services.NPCService
import com.mudndcapstone.server.services.SessionService
import com.mudndcapstone.server.services.UserService
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
    @Autowired SessionService sessionService
    @Autowired UserService userService

    /* Enemies */
    @GetMapping("/enemies")
    ResponseEntity<Set<EnemyDto>> getAllEnemies() {
        Set<Enemy> enemies = enemyService.getAllEnemies()
        Set<EnemyDto> enemyDtos = enemyService.buildDtoSetFrom(enemies)
        new ResponseEntity<>(enemyDtos, HttpStatus.OK)
    }

    @PostMapping("/sessions/{sessionId}/enemies")
    ResponseEntity<EnemyDto> createEnemy(@PathVariable String sessionId, @Valid @RequestBody EnemyDto enemyDto) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.SESSION_NOT_FOUND_EXCEPTION)

        User dm = userService.getUserById(enemyDto.dmId)
        if (!dm) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.USER_NOT_FOUND_EXCEPTION)

        Enemy enemy = enemyService.buildAndCreateEnemy(enemyDto, session, dm)
        if (!enemy) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.ENEMY_NOT_CREATED_EXCEPTION)

        EnemyDto created = enemyService.buildDtoFrom(enemy)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

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

    /* NPCs */
    @GetMapping("/npcs")
    ResponseEntity<Set<NPCDto>> getAllNPCs() {
        Set<NPC> npcs = npcService.getAllNPCs()
        Set<NPCDto> npcDtos = npcService.buildDtoSetFrom(npcs)
        new ResponseEntity<>(npcDtos, HttpStatus.OK)
    }

    @PostMapping("/sessions/{sessionId}/npcs")
    ResponseEntity<NPCDto> createNPC(@PathVariable String sessionId, @Valid @RequestBody NPCDto npcDto) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.SESSION_NOT_FOUND_EXCEPTION)

        User dm = userService.getUserById(npcDto.dmId)
        if (!dm) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.USER_NOT_FOUND_EXCEPTION)

        NPC npc = npcService.buildAndCreateNPC(npcDto, session, dm)
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
