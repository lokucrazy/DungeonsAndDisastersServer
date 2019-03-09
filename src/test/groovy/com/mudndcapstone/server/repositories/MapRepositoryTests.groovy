package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Map
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class MapRepositoryTests {

    @Autowired
    private MapRepository mapRepository

    @Test
    void whenFindById_returnMap() {
        //Given
        Map map = new Map()
        mapRepository.save(map)
        //When
        Optional<Map> found = mapRepository.findById(map.identifier)
        //Then
        Assert.assertEquals(found.get().id, map.id)
    }
}
