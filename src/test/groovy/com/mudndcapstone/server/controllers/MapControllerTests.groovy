package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.services.MapService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class MapControllerTests {

    @Mock
    MapService mapService

    @InjectMocks
    MapController mapController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenGetAllMaps_returnMapList() {
        //Given
        List<Map> maps = new ArrayList<Map>()
        Mockito.when(mapService.getAllMaps()).thenReturn(maps.asList())
        //Then
        ResponseEntity response = mapController.getAllMaps()
        Assert.assertEquals(response.statusCode, HttpStatus.OK)
        Assert.assertEquals(response.body, maps)
        Mockito.verify(mapService, Mockito.atLeastOnce()).getAllMaps()
    }
}
