package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.request.UserRequest
import com.mudndcapstone.server.repositories.UserRepository
import com.mudndcapstone.server.services.UserService
import com.mudndcapstone.server.utils.ModelBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl implements UserService {

    @Autowired UserRepository userRepository

    @Override
    List<User> getAllUsers() {
        userRepository.findAll().asList()
    }

    @Override
    User getUserById(Long id) {
        userRepository.findById(id).orElse(null)
    }

    @Override
    User createUser(UserRequest userRequest) {
        User user = ModelBuilder.buildUserFrom(userRequest)
        userRepository.save(user)
    }

    @Override
    void deleteUserById(Long id) {
        userRepository.deleteById(id)
    }

}
