package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.models.dto.UserDto
import com.mudndcapstone.server.services.impl.CharacterServiceImpl
import com.mudndcapstone.server.services.impl.UserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RestController
class UserController {

    @Autowired UserServiceImpl userService
    @Autowired CharacterServiceImpl characterService

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
    ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId)
        if (!user) return new ResponseEntity<>(HttpStatus.BAD_REQUEST)

        UserDto userDto = userService.buildDtoFrom(user)
        new ResponseEntity<>(userDto, HttpStatus.OK)
    }

    @PutMapping("/users/{userId}")
    ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @Valid @RequestBody UserDto userDto) {
        new ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @DeleteMapping("/users/{userId}")
    ResponseEntity deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId)
        new ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/users/{userId}/characters")
    ResponseEntity<Set<CharacterDto>> getAllUsersCharacters(@PathVariable Long userId) {
        User user = userService.getUserById(userId)
        if (!user) return new ResponseEntity<>(HttpStatus.BAD_REQUEST)
        if (!user.characters) return new ResponseEntity<>([], HttpStatus.OK)

        Set<CharacterDto> characterDtos = characterService.buildDtoSetFrom(user.characters)
        new ResponseEntity<>(characterDtos, HttpStatus.OK)
    }

    /* DMs */
    @GetMapping("/dms")
    ResponseEntity<Set<UserDto>> getAllDMs() {
        Set<User> dms = userService.getAllDMs()
        Set<UserDto> dmDtos = userService.buildDtoSetFrom(dms)
        new ResponseEntity<>(dmDtos, HttpStatus.OK)
    }

}
