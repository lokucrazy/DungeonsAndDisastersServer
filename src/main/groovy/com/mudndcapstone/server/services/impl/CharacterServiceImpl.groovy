package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.repositories.CharacterRepository
import com.mudndcapstone.server.services.CharacterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CharacterServiceImpl implements CharacterService {

    @Autowired CharacterRepository characterRepository

    @Override
    List<Character> getAllCharacters() {
        characterRepository.findAll().asList()
    }

    @Override
    Character getCharacterById(Long id) {
        characterRepository.findById(id).orElse(null)
    }

    @Override
    Character createCharacter(Character character) {
        characterRepository.save(character)
    }

    @Override
    void deleteCharacter(Long id) {
        characterRepository.deleteById(id)
    }
}
