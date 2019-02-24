package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Map

interface MapService {
    List<Map> getAllMaps()
    Map getMapById(Long id)
    Map createMap(Map map)
    void deleteMap(Long id)

}