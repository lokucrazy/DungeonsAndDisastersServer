package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
class User extends Node {
    String username
    String password
    Date birthdate
    List<String> notes

    @Relationship(type = "CREATED_CHAR", direction = Relationship.OUTGOING)
    List<Character> characters

    @Relationship(type = "HAS_PLAYER", direction = Relationship.INCOMING)
    List<Session> sessions

    @Relationship(type = "HAS_DM", direction = Relationship.INCOMING)
    List<Session> dmSessions

    @Relationship(type = "CONTROLS", direction = Relationship.OUTGOING)
    List<NPC> npcs
}