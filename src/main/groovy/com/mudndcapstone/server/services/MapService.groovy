package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.models.request.MapRequest

interface MapService {
    List<Map> getAllMaps()
    Map getMapById(Long id)
    Map createMap(MapRequest mapRequest)
    void deleteMap(Long id)

}