package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.request.UserRequest

interface UserService {
    List<User> getAllUsers()
    User getUserById(Long id)
    User createUser(UserRequest user)
    void deleteUserById(Long id)
}
