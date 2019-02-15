package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
class Session {
    @Id @GeneratedValue Long id


    @Relationship(type="PLAYER", direction=Relationship.OUTGOING)
    Set<User> users

    @Relationship(type="DM", direction=Relationship.OUTGOING)
    User dm

    @Relationship(type="CHARACTERINSESSION", direction=Relationship.OUTGOING)
    Set<Character> characters

    @Relationship(type="MAPLIST", direction=Relationship.OUTGOING)
    Map map

    @Relationship(type="CHATLOG", direction=Relationship.OUTGOING)
    Chat chat

    @Relationship(type="COMBATLIST", direction=Relationship.OUTGOING)
    Combat combat

    @Relationship(type="HISTORY", direction=Relationship.OUTGOING)
    History history
}
