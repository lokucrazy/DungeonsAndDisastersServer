package com.mudndcapstone.server.models.dto

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class EntityDtoTests {

    @Test
    void givenEmptyEntityDto_thenReturnEmptyEntityDtoObject() {
        // Given
        EntityDto entityDto = new EntityDto()

        // Then
        Assert.assertNull(entityDto.identifier)
    }

}
