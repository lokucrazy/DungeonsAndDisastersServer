package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.request.UserRequest
import com.mudndcapstone.server.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RestController
class UserController {

    @Autowired UserService userService

    /* Users */
    @GetMapping(value = "/users")
    ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers()
        new ResponseEntity<>(allUsers, HttpStatus.OK)
    }

    @PostMapping(value = "/users")
    ResponseEntity<User> createUser(@Valid @RequestBody UserRequest user) {
        User created = userService.createUser(user)
        new ResponseEntity<>(created, HttpStatus.CREATED)
    }

    @GetMapping(value = "/users/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id)
        new ResponseEntity<>(user, HttpStatus.OK)
    }

    @DeleteMapping(value = "/users/{id}")
    ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id)
        new ResponseEntity(HttpStatus.OK)
    }

    /* DMs*/
    @GetMapping(value = "/dms")
    ResponseEntity<List<User>> getAllDMs() {
        List<User> allDMs = userService.getAllDMs()
        new ResponseEntity<>(allDMs, HttpStatus.OK)
    }

}
