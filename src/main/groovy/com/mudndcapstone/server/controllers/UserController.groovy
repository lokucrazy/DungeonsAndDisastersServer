package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.UserRequest
import com.mudndcapstone.server.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RestController
@RequestMapping(value = "/users")
class UserController {

    @Autowired UserService userService

    @GetMapping
    ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers()
        new ResponseEntity<>(allUsers, HttpStatus.OK)
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id)
        user ?
                new ResponseEntity<>(user, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<User> createUser(@Valid @RequestBody UserRequest user) {
        User created = userService.createUser(user)
        new ResponseEntity<>(created, HttpStatus.CREATED)
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id)
        new ResponseEntity(HttpStatus.OK)
    }

}
