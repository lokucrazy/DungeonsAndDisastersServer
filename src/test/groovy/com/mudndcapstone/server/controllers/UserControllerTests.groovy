package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.UserDto
import com.mudndcapstone.server.services.impl.UserServiceImpl
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
        Long userId = 500
        UserDto userDto
        ResponseEntity response

        // When
        user.setIdentifier(userId)
        userDto = userService.buildDtoFrom(user)
        Mockito.when(userService.getUserById(userId)).thenReturn(user)
        response = userController.getUserById(userId)

        // Then
        assert response.statusCode == HttpStatus.OK
        assert response.body == userDto
        Mockito.verify(userService, Mockito.atLeastOnce()).getUserById(userId)
    }

}
