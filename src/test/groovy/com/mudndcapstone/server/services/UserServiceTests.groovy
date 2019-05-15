package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.repositories.UserRepository
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
class UserServiceTests {

    @Mock UserRepository userRepository

    @InjectMocks
    UserService userService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenUser_whenUserRepositorySavesUser_thenUserServiceReturnsUser() {
        // Given
        User user = new User()
        User found

        // When
        userRepository.save(user)
        Mockito.when(userRepository.findById(user.identifier)).thenReturn(Optional.of(user))
        found = userService.getUserById(user.identifier)

        // Then
        assert user == found
    }

}
