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

    @Mock
    private UserRepository userRepository

    @InjectMocks
    private UserServiceImpl userService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void whenGetUserById_returnUser() {
        //Given
        User user = new User()
        userRepository.save(user)
        //When
        Mockito.when(userRepository.findById(user.id).orElse(null)).thenReturn(Optional.of(user))
        //Then
        Assert.assertEquals(userService.getUserById(),user)
    }
}
