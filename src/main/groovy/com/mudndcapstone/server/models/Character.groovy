package com.mudndcapstone.server.models

import com.mudndcapstone.server.utils.BeingAbilities
import com.mudndcapstone.server.utils.CharacterAlignment
import com.mudndcapstone.server.utils.CharacterClass
import com.mudndcapstone.server.utils.CharacterRace
import org.neo4j.ogm.annotation.*
import org.springframework.data.annotation.CreatedBy

@NodeEntity
class Character extends CharacterRequest {
    @Id @GeneratedValue Long id
    int level
    int experience
    BeingAbilities abilities

    @Relationship(type = "CREATED_CHARACTER", direction = Relationship.INCOMING)
    @CreatedBy User user

    @Relationship(type = "HAS_CHARACTER", direction = Relationship.INCOMING)
    List<Session> sessions

    static Character from(CharacterRequest characterRequest) {
        Character character = new Character()

        character.setCharacterClass(characterRequest.characterClass)
        character.setBackground(characterRequest.background)
        character.setRace(characterRequest.race)
        character.setAlignment(characterRequest.alignment)

        character
    }
}
