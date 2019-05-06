package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.UserDto
import com.mudndcapstone.server.repositories.UserRepository
import com.mudndcapstone.server.utils.Auditor
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class UserService {

    @Autowired UserRepository userRepository
    @Autowired ModelMapper modelMapper

    /* Users */
    Set<User> getAllUsers() {
        userRepository.findAll().toSet()
    }

    User getUserById(String id) {
        userRepository.findById(id).orElse(null)
    }

    User getUserByUserName(String username) {
        userRepository.findByUsername(username).orElse(null)
    }

    boolean existsByUsername(String username) {
        userRepository.findByUsername(username).orElse(null)
    }

    User upsertUser(User user) {
        Auditor.enableAuditing(user)
        userRepository.save(user)
    }

    User buildAndCreateUser(UserDto userDto) {
        User userRequest = buildUserFrom(userDto)
        User user = upsertUser(userRequest)
      
        user
    }

    User addNote(User user, String note) {
        if (!user) return null
        if (!note) return user

        user.notes ? (user.notes << note) : (user.notes = [note])

        Auditor.updateAuditing(user)
        userRepository.save(user)
    }

    void deleteUserById(String id) {
        userRepository.deleteById(id)
    }

    /* DMs */
    Set<User> getAllDMs() {
        userRepository.findAllDMs().toSet()
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

        Set<String> characterIds = user.characters ?
                user.characters.stream().map({ character -> character.identifier }).collect(Collectors.toSet()) :
                null
        Set<String> sessionIds = user.sessions ?
                user.sessions.stream().map({ session -> session.identifier }).collect(Collectors.toSet()) :
                null
        Set<String> dmSessionIds = user.dmSessions ?
                user.dmSessions.stream().map({ dmSession -> dmSession.identifier }).collect(Collectors.toSet()) :
                null
        Set<String> npcIds = user.npcs ?
                user.npcs.stream().map({ npc -> npc.identifier }).collect(Collectors.toSet()) :
                null

        userDto.setCharacterIds(characterIds)
        userDto.setSessionIds(sessionIds)
        userDto.setDmSessionIds(dmSessionIds)
        userDto.setNpcIds(npcIds)

        userDto
    }

    Set<UserDto> buildDtoSetFrom(Set<User> users) {
        if (!users) return []
        users.stream().map({ user -> buildDtoFrom(user) }).collect(Collectors.toSet())
    }

}
