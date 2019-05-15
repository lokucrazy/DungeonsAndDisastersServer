package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.dto.MapDto
import com.mudndcapstone.server.services.MapService
import com.mudndcapstone.server.services.SessionService
import com.mudndcapstone.server.utils.Exceptions
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
import org.springframework.web.server.ResponseStatusException

@RunWith(SpringRunner)
@SpringBootTest
class MapControllerTests {

    @Mock MapService mapService
    @Mock SessionService sessionService

    @InjectMocks
    MapController mapController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void getResponseEntity_whenGivenSessionId_forGetSessionMap() {
        // Mocks
        Mockito.when(sessionService.getSessionById("test")).thenReturn(new Session(map: new Map()))
        Mockito.when(mapService.buildDtoFrom(Mockito.any(Map))).thenReturn(new MapDto())

        // Test
        ResponseEntity<MapDto> responseEntity = mapController.getSessionMap("test")

        // Assert
        assert responseEntity != null
        assert responseEntity.statusCode == HttpStatus.OK
        assert responseEntity.body != null
    }

    @Test
    void getResponseStatusException_whenGivenBadSessionId_forGetSessionMap() {
        // Mocks
        Mockito.when(sessionService.getSessionById("bad")).thenReturn(null)

        // Test
        ResponseStatusException responseStatusException
        try {
            mapController.getSessionMap("bad")
        } catch (ResponseStatusException ex) {
            responseStatusException = ex
        }

        // Assert
        assert responseStatusException != null
        assert responseStatusException.status == HttpStatus.BAD_REQUEST
        assert responseStatusException.reason == Exceptions.SESSION_NOT_FOUND_EXCEPTION
    }
}
