package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.models.NPC
import com.mudndcapstone.server.services.EnemyService
import com.mudndcapstone.server.services.NPCService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BeingController {

    @Autowired NPCService npcService
    @Autowired EnemyService enemyService

    @GetMapping(value = "/enemies")
    ResponseEntity<List<Enemy>> getAllEnemies() {
        List<Enemy> allEnemies = enemyService.getAllEnemies()
        new ResponseEntity<>(allEnemies, HttpStatus.OK)
    }

    @GetMapping(value = "/npcs")
    ResponseEntity<List<NPC>> getAllNPCs() {
        List<NPC> allNPCs = npcService.getAllNPCs()
        new ResponseEntity<>(allNPCs, HttpStatus.OK)
    }

}
