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

    @Autowired MapRepository mapRepository

    @Test
    void givenMap_whenMapSavedToRepository_thenMapReturned() {
        // Given
        Map map = new Map()
        Map found

        // When
        mapRepository.save(map)
        found = mapRepository.findById(map.identifier).orElse(null)

        // Then
        Assert.assertNotNull(found)
        Assert.assertEquals(found.identifier, map.identifier)
    }

}
