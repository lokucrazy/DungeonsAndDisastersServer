package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.services.MapService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/maps")
class MapController {

    @Autowired MapService mapService

    @GetMapping
    ResponseEntity<List<Map>> getAllMaps() {
        List<Map> allMaps = mapService.getAllMaps()
        new ResponseEntity<>(allMaps, HttpStatus.OK)
    }

}
