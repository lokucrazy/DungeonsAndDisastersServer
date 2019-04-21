package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.dto.MapDto
import com.mudndcapstone.server.repositories.MapRepository
import com.mudndcapstone.server.utils.Auditor
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class MapService {

    @Autowired MapRepository mapRepository
    @Autowired ModelMapper modelMapper

    Set<Map> getAllMaps() {
        mapRepository.findAll().toSet()
    }

    Map getMapById(String id) {
        mapRepository.findById(id).orElse(null)
    }

    Map createMap(Map map) {
        if (!map.session) return null

        Auditor.enableAuditing(map)
        mapRepository.save(map)
    }

    void deleteMap(String id) {
        mapRepository.deleteById(id)
    }

    Map buildMapFrom(MapDto mapDto, Session session) {
        Map map = modelMapper.map(mapDto, Map)

        map.session = session

        map
    }

    MapDto buildDtoFrom(Map map) {
        MapDto mapDto = modelMapper.map(map, MapDto)

        String sessionId = map.session ? map.session.identifier : null
        mapDto.setSessionId(sessionId)

        mapDto
    }

    Set<MapDto> buildDtoSetFrom(Set<Map> maps) {
        if (!maps) return []
        maps.stream().map({ map -> buildDtoFrom(map) }).collect(Collectors.toSet())
    }

}
