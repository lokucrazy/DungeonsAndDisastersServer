package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.models.NPC
import com.mudndcapstone.server.services.CharacterService
import com.mudndcapstone.server.services.EnemyService
import com.mudndcapstone.server.services.NPCService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/beings")
class BeingController {

    @Autowired CharacterService characterService

    @Autowired NPCService npcService

    @Autowired EnemyService enemyService

    @GetMapping(value = "/characters")
    ResponseEntity<List<Character>> getAllCharacters() {
        List<Character> allCharacters = characterService.getAllCharacters()
        new ResponseEntity<List<Character>>(allCharacters, HttpStatus.OK)
    }

    @GetMapping(value = "/enemies")
    ResponseEntity<List<Enemy>> getAllEnemies() {
        List<Enemy> allEnemies = enemyService.getAllEnemies()
        new ResponseEntity<List<Enemy>>(allEnemies, HttpStatus.OK)
    }

    @GetMapping(value = "/npcs")
    ResponseEntity<List<NPC>> getAllNPCs() {
        List<NPC> allNPCs = npcService.getAllNPCs()
        new ResponseEntity<List<NPC>>(allNPCs,HttpStatus.OK)
    }
}
