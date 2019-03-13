package com.mudndcapstone.server.models

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class MapTests {

    @Test
    void givenEmptyMap_thenReturnEmptyMapObject() {
        // Given
        Map map = new Map()

        // Then
        assert !map.identifier
        assert !map.images
        assert !map.session
    }

    @Test
    void givenMap_whenAddProperties_thenMapObjectHasProperties() {
        // Given
        Map map = new Map()
        List<String> images = ["test image 1"]
        Session session = new Session()

        // When
        map.setImages(images)
        map.setSession(session)

        // Then
        assert !map.identifier
        assert map.images == images
        assert map.session == session
    }

}
