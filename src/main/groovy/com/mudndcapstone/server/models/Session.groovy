package com.mudndcapstone.server.models


import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
class Session extends History {
    @Relationship(type = "HAS_CHAT_LOG", direction = Relationship.OUTGOING)
    Chat chatLog

    @Relationship(type = "HAS_MAP_LIST", direction = Relationship.OUTGOING)
    Map mapList

    @Relationship(type = "HAS_COMBAT_LIST", direction = Relationship.OUTGOING)
    Combat combatList

    @Relationship(type = "HAS_DM", direction = Relationship.OUTGOING)
    DM dm

    @Relationship(type = "HAS_NPC", direction = Relationship.OUTGOING)
    Set<NPC> npcs

    @Relationship(type = "HAS_PLAYER", direction = Relationship.OUTGOING)
    Set<User> players

    @Relationship(type = "HAS_CHARACTER", direction = Relationship.OUTGOING)
    Set<Character> characters
}
