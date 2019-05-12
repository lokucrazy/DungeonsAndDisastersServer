package com.mudndcapstone.server.models

import com.mudndcapstone.server.utils.BeingAbilities
import com.mudndcapstone.server.utils.CharacterAlignment
import com.mudndcapstone.server.utils.CharacterClass
import com.mudndcapstone.server.utils.CharacterRace
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import org.neo4j.ogm.annotation.Relationship
import org.springframework.data.annotation.CreatedBy

import javax.validation.constraints.NotNull

@NodeEntity
class Character extends Being {
    @NotNull int level
    @NotNull int experience
    @NotNull @Property(name = "class") CharacterClass characterClass
    @NotNull String background
    @NotNull CharacterRace race
    @NotNull CharacterAlignment alignment
    @NotNull BeingAbilities abilities

    @NotNull
    @Relationship(type = "CREATED_CHARACTER", direction = Relationship.INCOMING)
    @CreatedBy User user

    @Relationship(type = "HAS_CHARACTER", direction = Relationship.INCOMING)
    Set<Session> sessions
}
