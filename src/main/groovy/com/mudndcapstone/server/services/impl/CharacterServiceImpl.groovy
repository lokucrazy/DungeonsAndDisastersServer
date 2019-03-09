package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.request.CharacterRequest
import com.mudndcapstone.server.repositories.CharacterRepository
import com.mudndcapstone.server.services.CharacterService
import com.mudndcapstone.server.utils.ModelBuilder
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
    Character createCharacter(CharacterRequest characterRequest) {
        Character character = ModelBuilder.buildCharacterFrom(characterRequest)
        characterRepository.save(character)
    }

    @Override
    void deleteCharacter(Long id) {
        characterRepository.deleteById(id)
    }
}
