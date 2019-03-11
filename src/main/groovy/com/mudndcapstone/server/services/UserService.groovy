package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.User

interface UserService {
    List<User> getAllUsers()
    User getUserById(Long id)
    User createUser(User user)
    void deleteUserById(Long id)

    List<User> getAllDMs()
    User getDMById(Long id)
}
