package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.repositories.CharacterRepository
import com.mudndcapstone.server.utils.Auditor
import com.mudndcapstone.server.utils.BeingAbilities
import com.mudndcapstone.server.utils.character.CharacterAttack
import com.mudndcapstone.server.utils.character.CharacterEquipment
import com.mudndcapstone.server.utils.character.CharacterHealth
import com.mudndcapstone.server.utils.character.CharacterMonies
import com.mudndcapstone.server.utils.character.CharacterSavingThrow
import com.mudndcapstone.server.utils.character.CharacterSkill
import com.mudndcapstone.server.utils.character.enums.SavingThrow
import com.mudndcapstone.server.utils.character.enums.Skills
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

        BeingAbilities abilities = new BeingAbilities()
        CharacterHealth health = new CharacterHealth()
        CharacterMonies monies = new CharacterMonies()

        characterRequest.abilities = abilities
        characterRequest.savingThrows = [
                new CharacterSavingThrow(SavingThrow.TEST_ST_1, 100, 0, true),
                new CharacterSavingThrow(SavingThrow.TEST_ST_2, 0, 0, false)
        ]
        characterRequest.skills = [
                new CharacterSkill(Skills.TEST_S_1, 0, 0, false)
        ]
        characterRequest.health = health
        characterRequest.attacks = [
                new CharacterAttack("wreck", 80, 10),
                new CharacterAttack("punch", 10, 0),
                new CharacterAttack("kick in nuts", 250, 250)
        ]
        characterRequest.equipment = [
                new CharacterEquipment("axe"),
                new CharacterEquipment("shield")
        ]
        characterRequest.monies = monies

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
