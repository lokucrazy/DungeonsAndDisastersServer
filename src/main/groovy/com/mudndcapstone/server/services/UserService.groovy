package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.User

interface UserService {
    List<User> getAllUsers()
    User getUserById(Long id)
    User createUser(String username, String password, Date birthdate)
    void deleteUserById(Long id)
}
