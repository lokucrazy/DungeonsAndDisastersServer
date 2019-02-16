package com.mudndcapstone.server.models

import com.mudndcapstone.server.utils.BeingAbilities
import com.mudndcapstone.server.utils.CharacterAlignment
import com.mudndcapstone.server.utils.CharacterClass
import com.mudndcapstone.server.utils.CharacterRace
import org.neo4j.ogm.annotation.*
import org.springframework.data.annotation.CreatedBy

@NodeEntity
class Character extends Being {
    @Id @GeneratedValue Long id
    @Property(name = "class") CharacterClass characterClass
    int level
    int experience
    String background
    CharacterRace race
    CharacterAlignment alignment
    BeingAbilities abilities

    @Relationship(type = "CREATED_CHARACTER", direction = Relationship.INCOMING)
    @CreatedBy
    User user

    @Relationship(type = "HAS_CHARACTER", direction = Relationship.INCOMING)
    List<Session> sessions
}
