package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.Index
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import org.neo4j.ogm.annotation.Required

@NodeEntity
class User extends Node {
    @Required @Index(unique = true) String username
    @Required String password
    @Required Date birthdate
    List<String> notes

    @Relationship(type = "CREATED_CHARACTER", direction = Relationship.OUTGOING)
    Set<Character> characters

    @Relationship(type = "HAS_PLAYER", direction = Relationship.INCOMING)
    Set<Session> sessions

    @Relationship(type = "HAS_DM", direction = Relationship.INCOMING)
    Set<Session> dmSessions

    @Relationship(type = "CONTROLS", direction = Relationship.OUTGOING)
    Set<NPC> npcs
}
