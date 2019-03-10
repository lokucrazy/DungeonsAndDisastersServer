package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.models.dto.MapDto
import com.mudndcapstone.server.repositories.MapRepository
import com.mudndcapstone.server.services.MapService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class MapServiceImpl implements MapService {

    @Autowired MapRepository mapRepository
    @Autowired ModelMapper modelMapper

    @Override
    List<Map> getAllMaps() {
        mapRepository.findAll().asList()
    }

    @Override
    Map getMapById(Long id) {
        mapRepository.findById(id).orElse(null)
    }

    @Override
    Map createMap(Map map) {
        mapRepository.save(map)
    }

    @Override
    void deleteMap(Long id) {
        mapRepository.deleteById(id)
    }

    Map buildMapFrom(MapDto mapDto) {
        Map map = modelMapper.map(mapDto, Map)

        map
    }

    MapDto buildDtoFrom(Map map) {
        MapDto mapDto = modelMapper.map(map, MapDto)

        Long sessionId = map.session ? map.session.identifier : null

        mapDto.setSessionId(sessionId)

        mapDto
    }

    List<MapDto> buildDtoListFrom(List<Map> maps) {
        maps.stream().map({ map -> buildDtoFrom(map) }).collect(Collectors.toList())
    }

}
