package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.repositories.MapRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class MapServiceImplTests {

    @Mock MapRepository mapRepository

    @InjectMocks
    MapServiceImpl mapService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenMap_whenMapRepositorySavesMap_thenMapServiceReturnsMap() {
        // Given
        Map map = new Map()
        Map found

        // When
        mapRepository.save(map)
        Mockito.when(mapRepository.findById(map.identifier)).thenReturn(Optional.of(map))
        found = mapService.getMapById(map.identifier)

        // Then
        assert map == found
    }

}
