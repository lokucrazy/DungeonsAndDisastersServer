package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.dto.MapDto
import com.mudndcapstone.server.services.MapService
import com.mudndcapstone.server.services.SessionService
import com.mudndcapstone.server.utils.Exceptions
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

@RestController
@CrossOrigin("*")
class MapController {

    @Autowired MapService mapService
    @Autowired SessionService sessionService

    @PutMapping("/maps/{mapId}")
    ResponseEntity<MapDto> updateMap(@PathVariable String mapId, @Valid @RequestBody MapDto mapDto) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, Exceptions.ROUTE_NOT_IMPLEMENTED)
    }

    @GetMapping("/sessions/{sessionId}/maps")
    ResponseEntity<MapDto> getSessionMap(@PathVariable String sessionId) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.SESSION_NOT_FOUND_EXCEPTION)

        Map map = session.map
        if (!map) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.MAP_NOT_FOUND_EXCEPTION)

        MapDto mapDto = mapService.buildDtoFrom(map)
        new ResponseEntity<>(mapDto, HttpStatus.OK)
    }

    @PostMapping("/maps/{mapId}/images")
    ResponseEntity<MapDto> addMapImage(@PathVariable String mapId, @RequestParam MultipartFile image) {
        if (image.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.MAP_NOT_FOUND_EXCEPTION)
        Map map = mapService.getMapById(mapId)
        if (!map) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.SESSION_NOT_FOUND_EXCEPTION)

        map = mapService.addImage(map, image)
        if (!map) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.IMAGE_NOT_ADDED)

        MapDto mapDto = mapService.buildDtoFrom(map)
        new ResponseEntity<>(mapDto, HttpStatus.OK)
    }

    @GetMapping("/maps/{mapId}/images/{imageName}")
    ResponseEntity<String> getMapImage(@PathVariable String mapId, @PathVariable String imageName) {
        Map map = mapService.getMapById(mapId)
        if (!map) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.MAP_NOT_FOUND_EXCEPTION)

        String img = mapService.getImage(imageName)
        if (!img) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.IMAGE_NOT_FOUND)

        new ResponseEntity<>(img, HttpStatus.OK)
    }

    @DeleteMapping("/maps/{mapId}/images/{imageName}")
    ResponseEntity deleteImage(@PathVariable String mapId, @PathVariable String imageName) {
        Map map = mapService.getMapById(mapId)
        if (!map) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.MAP_NOT_FOUND_EXCEPTION)

        if (mapService.deleteImage(imageName)) {
            new ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.IMAGE_NOT_FOUND)
        }
    }
}
