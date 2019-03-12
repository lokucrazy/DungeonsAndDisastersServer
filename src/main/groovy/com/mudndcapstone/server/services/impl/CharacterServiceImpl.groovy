package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.repositories.CharacterRepository
import com.mudndcapstone.server.services.CharacterService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class CharacterServiceImpl {

    @Autowired CharacterRepository characterRepository
    @Autowired ModelMapper modelMapper

    Set<Character> getAllCharacters() {
        characterRepository.findAll().toSet()
    }

    Character getCharacterById(Long id) {
        characterRepository.findById(id).orElse(null)
    }

    Character createCharacter(Character character) {
        characterRepository.save(character)
    }

    void deleteCharacter(Long id) {
        characterRepository.deleteById(id)
    }

    Character buildCharacterFrom(CharacterDto characterDto) {
        Character character = modelMapper.map(characterDto, Character)

        character
    }

    CharacterDto buildDtoFrom(Character character) {
        CharacterDto characterDto = modelMapper.map(character, CharacterDto)

        Long userId = character.user ? character.user.id : null
        Set<Long> sessionIds = character.sessions ?
                character.sessions.stream().map({ session -> session.identifier }).collect(Collectors.toSet()) :
                null

        characterDto.setUserId(userId)
        characterDto.setSessionIds(sessionIds)

        characterDto
    }

    Set<CharacterDto> buildDtoSetFrom(Set<Character> characters) {
        characters.stream().map({ character -> buildDtoFrom(character) }).collect(Collectors.toSet())
    }

}
