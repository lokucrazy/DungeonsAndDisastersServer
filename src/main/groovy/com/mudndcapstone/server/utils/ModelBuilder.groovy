package com.mudndcapstone.server.utils

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.request.CharacterRequest
import com.mudndcapstone.server.models.request.UserRequest

class ModelBuilder {

    static Character buildCharacterFrom(CharacterRequest characterRequest) {
        Character character = new Character()

        character.setCharacterClass(characterRequest.characterClass)
        character.setBackground(characterRequest.background)
        character.setRace(characterRequest.race)
        character.setAlignment(characterRequest.alignment)

        character
    }

    static User buildUserFrom(UserRequest userRequest) {
        User user = new User()

        user.setUsername(userRequest.username)
        user.setPassword(userRequest.password)
        user.setBirthdate(userRequest.birthdate)

        user
    }
}
