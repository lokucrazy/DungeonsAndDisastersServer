package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.SessionDto
import com.mudndcapstone.server.models.dto.UserDto
import com.mudndcapstone.server.services.CharacterService
import com.mudndcapstone.server.services.SessionService
import com.mudndcapstone.server.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

@RestController
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
        if (!user) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)

        UserDto created = userService.buildDtoFrom(user)
        new ResponseEntity<>(created, HttpStatus.CREATED)
    }

    @GetMapping("/users/{userId}")
    ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        User user = userService.getUserById(userId)
        if (!user) return new ResponseEntity<>(HttpStatus.BAD_REQUEST)

        UserDto userDto = userService.buildDtoFrom(user)
        new ResponseEntity<>(userDto, HttpStatus.OK)
    }

    @PutMapping("/users/{userId}")
    ResponseEntity<UserDto> updateUser(@PathVariable String userId, @Valid @RequestBody UserDto userDto) {
        new ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @DeleteMapping("/users/{userId}")
    ResponseEntity deleteUser(@PathVariable String userId) {
        userService.deleteUserById(userId)
        new ResponseEntity(HttpStatus.OK)
    }

    @PutMapping("/sessions/{sessionId}/users/{userId}")
    ResponseEntity<SessionDto> connectUserToSession(@PathVariable String sessionId, @PathVariable String userId) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "session could not be found")

        User user = userService.getUserById(userId)
        if (!user) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user could not be found")

        session = sessionService.attachUserToSession(session, user)
        if (!session) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "could not attach user to session")

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
