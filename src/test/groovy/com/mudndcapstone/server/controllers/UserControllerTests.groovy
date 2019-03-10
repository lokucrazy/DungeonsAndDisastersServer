package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.UserDto
import com.mudndcapstone.server.services.impl.UserServiceImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class UserControllerTests {

    @Mock UserServiceImpl userService

    @InjectMocks
    UserController userController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenUser_whenUserServiceGetsUser_thenUserControllerReturnsUser() {
        // Given
        User user = new User()
        UserDto userDto = userService.buildDtoFrom(user)

        // When
        Mockito.when(userService.getUserById()).thenReturn(user)

        // Then
        ResponseEntity response = userController.getUserById()
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK)
        Assert.assertEquals(response.body, userDto)
        Mockito.verify(userService, Mockito.atLeastOnce()).getUserById()
    }

}
