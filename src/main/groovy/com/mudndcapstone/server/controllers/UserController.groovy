package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Messenger
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
    @PostMapping("/users")
    ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        boolean userExists = userService.existsByUsername(userDto.username)
        if (userExists) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.USERNAME_TAKEN_EXCEPTION)

        User user = userService.buildAndCreateUser(userDto)
        if (!user) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.USER_NOT_CREATED_EXCEPTION)

        UserDto created = userService.buildDtoFrom(user)
        new ResponseEntity<>(created, HttpStatus.CREATED)
    }

    @PostMapping("/login/{username}")
    ResponseEntity<UserDto> loginUser(@PathVariable String username, @RequestParam(required = true) String password) {
        if (!userService.existsByUsername(username)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.USER_NOT_FOUND_EXCEPTION)

        User user = userService.getUserByUserNameAndPassword(username, password)
        if (!user) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.USERNAME_PASSWORD_INCORRECT)

        UserDto userDto = userService.buildDtoFrom(user)
        new ResponseEntity<>(userDto, HttpStatus.OK)
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
        if (!userService.existsByUserId(userId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.USER_NOT_FOUND_EXCEPTION)
        if (userService.getUsernameById(userId) != userDto.username && userService.existsByUsername(userDto.username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.USERNAME_TAKEN_EXCEPTION)
        }
        User userRequest = userService.buildUserFrom(userDto)
        userRequest.identifier = userId
        User user

        user = userService.upsertUser(userRequest)
        if (!user) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.USER_NOT_UPDATED_EXCEPTION)

        UserDto updated = userService.buildDtoFrom(user)
        new ResponseEntity<>(updated, HttpStatus.OK)
    }

    @DeleteMapping("/users/{userId}")
    ResponseEntity deleteUser(@PathVariable String userId) {
        userService.deleteUserById(userId)
        new ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/users/{userId}/notes")
    ResponseEntity<List<String>> getUserNotes(@PathVariable String userId) {
        User user = userService.getUserById(userId)
        if (!user) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.USER_NOT_FOUND_EXCEPTION)

        new ResponseEntity<>(user.notes, HttpStatus.OK)
    }

    @PostMapping("/users/{userId}/notes")
    ResponseEntity<UserDto> addUserNote(@PathVariable String userId, @Valid @RequestBody Messenger messenger) {
        User user = userService.getUserById(userId)
        if (!user) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.USER_NOT_FOUND_EXCEPTION)

        User updated = userService.addNote(user, messenger.body)
        UserDto userDto = userService.buildDtoFrom(updated)
        new ResponseEntity<>(userDto, HttpStatus.OK)
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

}
