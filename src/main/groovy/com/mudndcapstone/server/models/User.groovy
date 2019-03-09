package com.mudndcapstone.server.models

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator, property = "identifier")
class User extends Node {
    String username
    String password
    Date birthdate
    List<String> notes

    @Relationship(type = "CREATED_CHAR", direction = Relationship.OUTGOING)
    Set<Character> characters

    @Relationship(type = "HAS_PLAYER", direction = Relationship.INCOMING)
    Set<Session> sessions

    @Relationship(type = "HAS_DM", direction = Relationship.INCOMING)
    Set<Session> dmSessions

    @Relationship(type = "CONTROLS", direction = Relationship.OUTGOING)
    Set<NPC> npcs
}