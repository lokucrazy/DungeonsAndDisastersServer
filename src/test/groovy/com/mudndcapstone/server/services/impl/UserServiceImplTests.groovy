package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.repositories.UserRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class UserServiceImplTests {

    @Mock UserRepository userRepository

    @InjectMocks
    UserServiceImpl userService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenUser_whenUserRepositorySavesUser_thenUserServiceReturnsUser() {
        // Given
        User user = new User()

        // When
        userRepository.save(user)
        Mockito.when(userRepository.findById(user.identifier)).thenReturn(Optional.of(user))

        // Then
        Assert.assertEquals(userService.getUserById(user.identifier), user)
    }

}
