package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.models.NPC
import com.mudndcapstone.server.models.dto.EnemyDto
import com.mudndcapstone.server.models.dto.NPCDto
import com.mudndcapstone.server.services.impl.EnemyServiceImpl
import com.mudndcapstone.server.services.impl.NPCServiceImpl

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

import javax.validation.Valid

@RestController
class BeingController {

    @Autowired NPCServiceImpl npcService
    @Autowired EnemyServiceImpl enemyService

    /* Enemies */
    @GetMapping("/enemies")
    ResponseEntity<List<EnemyDto>> getAllEnemies() {
        List<Enemy> enemies  = enemyService.getAllEnemies()
        List<EnemyDto> enemyDtos = enemyService.buildDtoListFrom(enemies)
        new ResponseEntity<>(enemyDtos, HttpStatus.OK)
    }

    @PostMapping("/enemies")
    ResponseEntity<EnemyDto> createEnemy(@Valid @RequestBody EnemyDto enemyDto) {
        Enemy enemyRequest = enemyService.buildEnemyFrom(enemyDto)
        Enemy enemy = enemyService.createEnemy(enemyRequest)
        if (!enemy) return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)

        EnemyDto created = enemyService.buildDtoFrom(enemy)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

    @GetMapping("/enemies/{enemyId}")
    ResponseEntity<EnemyDto> getEnemyById(@PathVariable String enemyId) {
        Enemy enemy = enemyService.getEnemyById(enemyId)
        if (!enemy) return new ResponseEntity(HttpStatus.BAD_REQUEST)

        EnemyDto enemyDto = enemyService.buildDtoFrom(enemy)
        new ResponseEntity<>(enemyDto, HttpStatus.OK)
    }

    @PutMapping("/enemies/{enemyId}")
    ResponseEntity<EnemyDto> updateEnemy(@PathVariable String enemyId, @Valid @RequestBody EnemyDto enemyDto) {
        new ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @DeleteMapping("/enemies/{enemyId}")
    ResponseEntity deleteEnemy(@PathVariable String enemyId) {
        enemyService.deleteEnemy(enemyId)
        new ResponseEntity(HttpStatus.OK)
    }

    /* NPCs */
    @GetMapping("/npcs")
    ResponseEntity<List<NPCDto>> getAllNPCs() {
        List<NPC> npcs = npcService.getAllNPCs()
        List<NPCDto> npcDtos = npcService.buildDtoListFrom(npcs)
        new ResponseEntity<>(npcDtos, HttpStatus.OK)
    }

    @PostMapping("/npcs")
    ResponseEntity<NPCDto> createNPC(@Valid @RequestBody NPCDto npcDto) {
        NPC npcRequest = npcService.buildNPCFrom(npcDto)
        NPC npc = npcService.createNPC(npcRequest)
        if (!npc) return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)

        NPCDto created = npcService.buildDtoFrom(npc)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

    @GetMapping("/npcs/{npcId}")
    ResponseEntity<NPCDto> getNPCById(@PathVariable String npcId) {
        NPC npc = npcService.getNPCById(npcId)
        if (!npc) return new ResponseEntity(HttpStatus.BAD_REQUEST)

        NPCDto npcDto = npcService.buildDtoFrom(npc)
        new ResponseEntity<>(npcDto, HttpStatus.OK)
    }

    @PutMapping("/npcs/{npcId}")
    ResponseEntity<NPCDto> updateNPC(@PathVariable String npcId, @Valid @RequestBody NPCDto npcDto) {
        new ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @DeleteMapping("/npcs/{npcId}")
    ResponseEntity deleteNPC(@PathVariable String npcId) {
        npcService.deleteNPC(npcId)
        new ResponseEntity(HttpStatus.OK)
    }

}
