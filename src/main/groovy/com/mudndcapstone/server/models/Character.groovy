package com.mudndcapstone.server.models

import com.mudndcapstone.server.utils.character.CharacterAlignment
import com.mudndcapstone.server.utils.character.CharacterClass
import com.mudndcapstone.server.utils.character.CharacterRace
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import org.neo4j.ogm.annotation.Relationship
import org.springframework.data.annotation.CreatedBy

@NodeEntity
class Character extends Being {
    int level
    int experience
    @Property(name = "class") CharacterClass characterClass
    String background
    CharacterRace race
    CharacterAlignment alignment
    String abilities

    @Relationship(type = "CREATED_CHARACTER", direction = Relationship.INCOMING)
    @CreatedBy User user

    @Relationship(type = "HAS_CHARACTER", direction = Relationship.INCOMING)
    Set<Session> sessions
}
