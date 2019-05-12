package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.repositories.CharacterRepository
import com.mudndcapstone.server.utils.Auditor
import com.mudndcapstone.server.utils.BeingAbilities
import com.mudndcapstone.server.utils.character.*
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class CharacterService {

    @Autowired CharacterRepository characterRepository
    @Autowired ModelMapper modelMapper

    Set<Character> getAllCharacters() {
        characterRepository.findAll().toSet()
    }

    Character getCharacterById(String id) {
        characterRepository.findById(id).orElse(null)
    }

    Character upsertCharacter(Character character) {
        Auditor.enableAuditing(character)
        characterRepository.save(character)
    }

    Character buildAndCreateCharacter(CharacterDto characterDto, User user) {
        Character characterRequest = buildCharacterFrom(characterDto, user)

        characterRequest.abilities = new BeingAbilities()
        characterRequest.health = new CharacterHealth()
        characterRequest.monies = new CharacterMonies()

        upsertCharacter(characterRequest)
    }

    void deleteCharacter(String id) {
        characterRepository.deleteById(id)
    }

    Character buildCharacterFrom(CharacterDto characterDto, User user) {
        Character character = modelMapper.map(characterDto, Character)

        character.user = user
        character.abilities = characterDto.abilities
        character.savingThrows = characterDto.savingThrows
        character.skills = characterDto.skills
        character.health = characterDto.health
        character.attacks = characterDto.attacks
        character.equipment = characterDto.equipment
        character.monies = characterDto.monies

        character
    }

    CharacterDto buildDtoFrom(Character character) {
        CharacterDto characterDto = modelMapper.map(character, CharacterDto)

        String userId = character.user ? character.user.identifier : null
        Set<String> sessionIds = character.sessions ?
                character.sessions.stream().map({ session -> session.identifier }).collect(Collectors.toSet()) :
                null
        BeingAbilities abilities = character.abilities
        List<CharacterSavingThrow> savingThrows = character.savingThrows
        List<CharacterSkill> skills = character.skills
        CharacterHealth health = character.health
        List<CharacterAttack> attacks = character.attacks
        List<CharacterEquipment> equipment = character.equipment
        CharacterMonies monies = character.monies

        characterDto.setUserId(userId)
        characterDto.setSessionIds(sessionIds)
        characterDto.setAbilities(abilities)
        characterDto.setSavingThrows(savingThrows)
        characterDto.setSkills(skills)
        characterDto.setHealth(health)
        characterDto.setAttacks(attacks)
        characterDto.setEquipment(equipment)
        characterDto.setMonies(monies)

        characterDto
    }

    Set<CharacterDto> buildDtoSetFrom(Set<Character> characters) {
        if (!characters) return []
        characters.stream().map({ character -> buildDtoFrom(character) }).collect(Collectors.toSet())
    }

}
