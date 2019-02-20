package com.mudndcapstone.server.models

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class BeingTests {

    @Test
    void givenChildClass_returnTrue() {
        Character character = new Character()

        Assert.assertEquals(true, (character instanceof Being))
    }
}
