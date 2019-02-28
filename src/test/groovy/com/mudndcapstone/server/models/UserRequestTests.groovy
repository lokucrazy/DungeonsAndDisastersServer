package com.mudndcapstone.server.models

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class UserRequestTests {

    @Test
    void givenEmptyUserRequest_thenReturnAlmostEmptyUserRequestObject() {
        // Given
        User user = new User()

        // Then
        Assert.assertNull(user.username)
        Assert.assertNull(user.password)
    }

}
