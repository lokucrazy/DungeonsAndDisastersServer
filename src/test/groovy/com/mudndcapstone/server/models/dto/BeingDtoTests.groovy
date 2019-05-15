package com.mudndcapstone.server.models.dto

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class BeingDtoTests {

    @Test
    void givenEmptyBeingDto_thenReturnEmptyBeingDtoObject() {
        // Given
        BeingDto beingDto = new BeingDto()

        // Then
        assert !beingDto.identifier
        assert !beingDto.name
    }

    @Test
    void givenBeingDto_whenAddProperties_thenBeingDtoObjectsHasProperties() {
        // Given
        BeingDto beingDto = new BeingDto()
        String name = "test"

        // When
        beingDto.setName(name)

        // Then
        assert !beingDto.identifier
        assert beingDto.name == name
    }

}
