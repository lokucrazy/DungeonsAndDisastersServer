package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.models.request.MapRequest
import com.mudndcapstone.server.repositories.MapRepository
import com.mudndcapstone.server.services.MapService
import com.mudndcapstone.server.utils.ModelBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MapServiceImpl implements MapService {

    @Autowired MapRepository mapRepository

    @Override
    List<Map> getAllMaps() {
        mapRepository.findAll().asList()
    }

    @Override
    Map getMapById(Long id) {
        mapRepository.findById(id).orElse(null)
    }

    @Override
    Map createMap(MapRequest mapRequest) {
        Map map = ModelBuilder.buildMapFrom(mapRequest)
        mapRepository.save(map)
    }

    @Override
    void deleteMap(Long id) {
        mapRepository.deleteById(id)
    }
}
