package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Character

interface CharacterService {
    List<Character> getAllCharacters()
    Character getCharacterById(Long id)
    Character createCharacter(Character character)
    void deleteCharacter(Long id)
}