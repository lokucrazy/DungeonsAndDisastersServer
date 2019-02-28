package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.CharacterRequest

interface CharacterService {
    List<Character> getAllCharacters()
    Character getCharacterById(Long id)
    Character createCharacter(CharacterRequest character)
    void deleteCharacter(Long id)
}