package com.mudndcapstone.server.models.dto

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
        assert !mapDto.identifier
        assert !mapDto.sessionId
        assert !mapDto.images
    }

    @Test
    void givenMapDto_whenAddProperties_thenMapDtoObjectHasProperties() {
        // Given
        MapDto mapDto = new MapDto()
        String testUuid = UUID.randomUUID().toString()
        List<String> images = ["test image 1"]

        // When
        mapDto.setSessionId(testUuid)
        mapDto.setImages(images)

        // Then
        assert !mapDto.identifier
        assert mapDto.sessionId == testUuid
        assert mapDto.images == images
    }

}
