package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.SessionDto
import com.mudndcapstone.server.models.dto.UserDto
import com.mudndcapstone.server.services.CharacterService
import com.mudndcapstone.server.services.SessionService
import com.mudndcapstone.server.services.UserService
import com.mudndcapstone.server.utils.Exceptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

@RestController
@CrossOrigin("*")
class UserController {

    @Autowired UserService userService
    @Autowired CharacterService characterService
    @Autowired SessionService sessionService

    /* Users */
    @GetMapping("/users")
    ResponseEntity<Set<UserDto>> getAllUsers() {
        Set<User> users = userService.getAllUsers()
        Set<UserDto> userDtos = userService.buildDtoSetFrom(users)
        new ResponseEntity<>(userDtos, HttpStatus.OK)
    }

    @PostMapping("/users")
    ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        User userRequest = userService.buildUserFrom(userDto)
        User user = userService.createUser(userRequest)
        if (!user) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.USER_NOT_CREATED_EXCEPTION)

        UserDto created = userService.buildDtoFrom(user)
        new ResponseEntity<>(created, HttpStatus.CREATED)
    }

    @GetMapping("/users/{userId}")
    ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        User user = userService.getUserById(userId)
        if (!user) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.USER_NOT_FOUND_EXCEPTION)

        UserDto userDto = userService.buildDtoFrom(user)
        new ResponseEntity<>(userDto, HttpStatus.OK)
    }

    @PutMapping("/users/{userId}")
    ResponseEntity<UserDto> updateUser(@PathVariable String userId, @Valid @RequestBody UserDto userDto) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, Exceptions.ROUTE_NOT_IMPLEMENTED)
    }

    @DeleteMapping("/users/{userId}")
    ResponseEntity deleteUser(@PathVariable String userId) {
        userService.deleteUserById(userId)
        new ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PutMapping("/sessions/{sessionId}/users/{userId}")
    ResponseEntity<SessionDto> connectUserToSession(@PathVariable String sessionId, @PathVariable String userId) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.SESSION_NOT_FOUND_EXCEPTION)

        User user = userService.getUserById(userId)
        if (!user) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.USER_NOT_FOUND_EXCEPTION)

        session = sessionService.attachUserToSession(session, user)
        if (!session) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.SESSION_CANT_CONNECT_USER_EXCEPTION)

        SessionDto sessionDto = sessionService.buildDtoFrom(session)
        new ResponseEntity<>(sessionDto, HttpStatus.OK)
    }

    /* DMs */
    @GetMapping("/dms")
    ResponseEntity<Set<UserDto>> getAllDMs() {
        Set<User> dms = userService.getAllDMs()
        Set<UserDto> dmDtos = userService.buildDtoSetFrom(dms)
        new ResponseEntity<>(dmDtos, HttpStatus.OK)
    }

}
