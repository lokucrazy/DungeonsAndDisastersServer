package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.UserDto
import com.mudndcapstone.server.repositories.UserRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class UserServiceImpl {

    @Autowired UserRepository userRepository
    @Autowired ModelMapper modelMapper

    /* Users */
    List<User> getAllUsers() {
        userRepository.findAll().asList()
    }

    User getUserById(String id) {
        userRepository.findById(id).orElse(null)
    }

    User createUser(User user) {
        userRepository.save(user)
    }

    void deleteUserById(String id) {
        userRepository.deleteById(id)
    }

    /* DMs */
    List<User> getAllDMs() {
        userRepository.findAllDMs().asList()
    }

    User getDMById(String id) {
        userRepository.findById(id).orElse(null)
    }

    User buildUserFrom(UserDto userDto) {
        User user = modelMapper.map(userDto, User)
        user
    }

    UserDto buildDtoFrom(User user) {
        UserDto userDto = modelMapper.map(user, UserDto)

        List<String> characterIds = user.characters ?
                user.characters.stream().map({ character -> character.identifier }).collect(Collectors.toList()) :
                null
        List<String> sessionIds = user.sessions ?
                user.sessions.stream().map({ session -> session.identifier }).collect(Collectors.toList()) :
                null
        List<String> dmSessionIds = user.dmSessions ?
                user.dmSessions.stream().map({ dmSession -> dmSession.identifier }).collect(Collectors.toList()) :
                null
        List<String> npcIds = user.npcs ?
                user.npcs.stream().map({ npc -> npc.identifier }).collect(Collectors.toList()) :
                null

        userDto.setCharacterIds(characterIds)
        userDto.setSessionIds(sessionIds)
        userDto.setDmSessionIds(dmSessionIds)
        userDto.setNpcIds(npcIds)

        userDto
    }

    List<UserDto> buildDtoListFrom(List<User> users) {
        users.stream().map({ user -> buildDtoFrom(user) }).collect(Collectors.toList())
    }

}
