package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.dto.MapDto
import com.mudndcapstone.server.services.MapService
import com.mudndcapstone.server.services.SessionService
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
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

@RestController
@RequestMapping("/maps")
class MapController {

    @Autowired MapService mapService
    @Autowired SessionService sessionService

    @GetMapping
    ResponseEntity<Set<MapDto>> getAllMaps() {
        Set<Map> maps = mapService.getAllMaps()
        Set<MapDto> mapDtos = mapService.buildDtoSetFrom(maps)
        new ResponseEntity<>(mapDtos, HttpStatus.OK)
    }

    @PostMapping
    ResponseEntity<MapDto> createMap(@Valid @RequestBody MapDto mapDto) {
        Session session = sessionService.getSessionById(mapDto.sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.SESSION_NOT_FOUND_EXCEPTION)

        Map mapRequest = mapService.buildMapFrom(mapDto, session)
        Map map = mapService.createMap(mapRequest)
        if (!map) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.MAP_NOT_CREATED_EXCEPTION)

        MapDto created = mapService.buildDtoFrom(map)
        new ResponseEntity<>(created, HttpStatus.OK)
    }

    @GetMapping("/{mapId}")
    ResponseEntity<MapDto> getMapById(@PathVariable String mapId) {
        Map map = mapService.getMapById(mapId)
        if (!map) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.MAP_NOT_FOUND_EXCEPTION)

        MapDto mapDto = mapService.buildDtoFrom(map)
        new ResponseEntity<>(mapDto, HttpStatus.OK)
    }

    @PutMapping("/{mapId}")
    ResponseEntity<MapDto> updateMap(@PathVariable String mapId, @Valid @RequestBody MapDto mapDto) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, Exceptions.ROUTE_NOT_IMPLEMENTED)
    }

    @DeleteMapping("/{mapId}")
    ResponseEntity deleteMap(@PathVariable String mapId) {
        mapService.deleteMap(mapId)
        new ResponseEntity(HttpStatus.NO_CONTENT)
    }

}
