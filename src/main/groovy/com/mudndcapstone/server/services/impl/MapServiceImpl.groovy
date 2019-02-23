package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.repositories.MapRepository
import com.mudndcapstone.server.services.MapService
import org.springframework.beans.factory.annotation.Autowired

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
    Map createMap(Map map) {
        mapRepository.save(map)
    }

    @Override
    void deleteMap(Long id) {
        mapRepository.deleteById(id)
    }
}
