package com.mudndcapstone.server.models.dto

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class MapDtoTests {

    @Test
    void givenEmptyMapDto_thenReturnEmptyMapDtoObject() {
        // Given
        MapDto mapDto = new MapDto()

        // Then
        Assert.assertNull(mapDto.identifier)
        Assert.assertNull(mapDto.sessionId)
        Assert.assertNull(mapDto.images)
    }

    @Test
    void givenMapDto_whenAddProperties_thenMapDtoObjectHasProperties() {
        // Given
        MapDto mapDto = new MapDto()
        List<String> images = ["test image 1"]

        // When
        mapDto.setSessionId(500)
        mapDto.setImages(images)

        // Then
        Assert.assertNull(mapDto.identifier)
        Assert.assertEquals(mapDto.sessionId, 500)
        Assert.assertEquals(mapDto.images, images)
    }

}
