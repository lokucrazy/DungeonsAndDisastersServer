package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.models.dto.MapDto
import com.mudndcapstone.server.services.impl.MapServiceImpl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid

@RestController
@RequestMapping("/maps")
class MapController {

    @Autowired MapServiceImpl mapService

    @GetMapping
    ResponseEntity<Set<MapDto>> getAllMaps() {
        Set<Map> maps = mapService.getAllMaps()
        Set<MapDto> mapDtos = mapService.buildDtoSetFrom(maps)
        new ResponseEntity<>(mapDtos, HttpStatus.OK)
    }

    @PostMapping
    ResponseEntity<MapDto> createMap(@Valid @RequestBody MapDto mapDto) {
        Map mapRequest = mapService.buildMapFrom(mapDto)
        Map map = mapService.createMap(mapRequest)
        if (!map) return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)

        MapDto created = mapService.buildDtoFrom(map)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

    @GetMapping("/{mapId}")
    ResponseEntity<MapDto> getMapById(@PathVariable String mapId) {
        Map map = mapService.getMapById(mapId)
        if (!map) return new ResponseEntity(HttpStatus.BAD_REQUEST)

        MapDto mapDto = mapService.buildDtoFrom(map)
        new ResponseEntity<>(mapDto, HttpStatus.OK)
    }

    @PutMapping("/{mapId}")
    ResponseEntity<MapDto> updateMap(@PathVariable String mapId, @Valid @RequestBody MapDto mapDto) {
        new ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @DeleteMapping("/{mapId}")
    ResponseEntity deleteMap(@PathVariable String mapId) {
        mapService.deleteMap(mapId)
        new ResponseEntity(HttpStatus.OK)
    }

}
