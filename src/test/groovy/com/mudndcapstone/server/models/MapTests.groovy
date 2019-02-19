package com.mudndcapstone.server.models

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class MapTests {

    @Test
    void givenEmptyMap_thenReturnAlmostEmptyMapObject() {
        // Given
        Map map = new Map()

        // Then
        Assert.assertNotNull(map.id)
        Assert.assertNull(map.images)
        Assert.assertNull(map.session)
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
        Assert.assertNotNull(map.id)
        Assert.assertEquals(map.images, images)
        Assert.assertEquals(map.session, session)
    }

}
