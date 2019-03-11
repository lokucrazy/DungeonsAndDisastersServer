package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.UserDto
import com.mudndcapstone.server.repositories.UserRepository
import com.mudndcapstone.server.services.UserService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class UserServiceImpl {

    @Autowired UserRepository userRepository
    @Autowired ModelMapper modelMapper

    /* Users */
    Set<User> getAllUsers() {
        userRepository.findAll().toSet()
    }

    User getUserById(Long id) {
        userRepository.findById(id).orElse(null)
    }

    User createUser(User user) {
        userRepository.save(user)
    }

    void deleteUserById(Long id) {
        userRepository.deleteById(id)
    }

    /* DMs */
    Set<User> getAllDMs() {
        userRepository.findAllDMs().toSet()
    }

    User getDMById(Long id) {
        userRepository.findById(id).orElse(null)
    }

    User buildUserFrom(UserDto userDto) {
        User user = modelMapper.map(userDto, User)

        user
    }

    UserDto buildDtoFrom(User user) {
        UserDto userDto = modelMapper.map(user, UserDto)

        HashSet<Long> characterIds = user.characters ?
                user.characters.stream().map({ character -> character.identifier }).collect(Collectors.toSet()) as HashSet :
                null
        HashSet<Long> sessionIds = user.sessions ?
                user.sessions.stream().map({ session -> session.identifier }).collect(Collectors.toSet()) as HashSet :
                null
        HashSet<Long> dmSessionIds = user.dmSessions ?
                user.dmSessions.stream().map({ dmSession -> dmSession.identifier }).collect(Collectors.toSet()) as HashSet :
                null
        HashSet<Long> npcIds = user.npcs ?
                user.npcs.stream().map({ npc -> npc.identifier }).collect(Collectors.toSet()) as HashSet :
                null

        userDto.setCharacterIds(characterIds)
        userDto.setSessionIds(sessionIds)
        userDto.setDmSessionIds(dmSessionIds)
        userDto.setNpcIds(npcIds)

        userDto
    }

    Set<UserDto> buildDtoSetFrom(Set<User> users) {
        users.stream().map({ user -> buildDtoFrom(user) }).collect(Collectors.toSet())
    }

}
