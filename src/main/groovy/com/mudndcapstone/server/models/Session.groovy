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
    User dm

    @Relationship(type = "HAS_NPC", direction = Relationship.OUTGOING)
    HashSet<NPC> npcs

    /* Could these two be a hashmap, mapping user => character
    or a new PlayerCharacter class
    */
    @Relationship(type = "HAS_PLAYER", direction = Relationship.OUTGOING)
    HashSet<User> players

    @Relationship(type = "HAS_CHARACTER", direction = Relationship.OUTGOING)
    HashSet<Character> characters
}
