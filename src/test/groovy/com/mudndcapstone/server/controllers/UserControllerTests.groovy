package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.services.UserService
import com.mudndcapstone.server.services.impl.UserServiceImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.client.HttpServerErrorException

@RunWith(SpringRunner)
@SpringBootTest
class UserControllerTests {

    @Mock
    private UserService userService

    @InjectMocks
    private UserController userController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenId_returnUser() throws Exception {
        //Given
        User user = new User()
        Mockito.when(userService.getUserById()).thenReturn(user)
        //Then
        ResponseEntity response = userController.getUserById()
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK)
        Assert.assertEquals(response.body, user)
        Mockito.verify(userService, Mockito.atLeastOnce()).getUserById()
    }

    @Test
    void givenBadId_returnNotFound() throws Exception {
        //Given
        Mockito.when(userService.getUserById()).thenReturn(null)
        //Then
        ResponseEntity response = userController.getUserById()
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND)
        Assert.assertNull(response.body)
        Mockito.verify(userService, Mockito.atLeastOnce()).getUserById()
    }
}
