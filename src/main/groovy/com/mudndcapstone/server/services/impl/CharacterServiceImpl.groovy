package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.repositories.CharacterRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class CharacterServiceImpl{

    @Autowired CharacterRepository characterRepository
    @Autowired ModelMapper modelMapper

    List<Character> getAllCharacters() {
        characterRepository.findAll().asList()
    }

    Character getCharacterById(String id) {
        characterRepository.findById(id).orElse(null)
    }

    Character createCharacter(Character character) {
        characterRepository.save(character)
    }

    void deleteCharacter(String id) {
        characterRepository.deleteById(id)
    }

    Character buildCharacterFrom(CharacterDto characterDto) {
        Character character = modelMapper.map(characterDto, Character)
        character
    }

    CharacterDto buildDtoFrom(Character character) {
        CharacterDto characterDto = modelMapper.map(character, CharacterDto)

        String userId = character.user ? character.user.identifier : null
        List<String> sessionIds = character.sessions ?
                character.sessions.stream().map({ session -> session.identifier }).collect(Collectors.toList()) :
                null


        characterDto.setUserId(userId)
        characterDto.setSessionIds(sessionIds)

        characterDto
    }

    List<CharacterDto> buildDtoListFrom(List<Character> characters) {
        characters.stream().map({ character -> buildDtoFrom(character) }).collect(Collectors.toList())
    }

}
