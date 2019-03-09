package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.services.UserService
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

    @Mock UserService userService

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

        // When
        Mockito.when(userService.getUserById()).thenReturn(user)

        // Then
        ResponseEntity response = userController.getUserById()
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK)
        Assert.assertEquals(response.body, user)
        Mockito.verify(userService, Mockito.atLeastOnce()).getUserById()
    }

    @Test
    void whenUserServiceGetsUserWithNoId_thenUserControllerReturnsError() {
        // When
        Mockito.when(userService.getUserById()).thenReturn(null)

        // Then
        ResponseEntity response = userController.getUserById()
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND)
        Assert.assertNull(response.body)
        Mockito.verify(userService, Mockito.atLeastOnce()).getUserById()
    }

}
