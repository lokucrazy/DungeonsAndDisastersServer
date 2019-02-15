package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
class User {
    @Id @GeneratedValue Long id

    @Relationship(type="HASCHARACTER", direction=Relationship.OUTGOING)
    Set<Character> characters

    @Relationship(type="HASNPC", direction=Relationship.OUTGOING)
    Set<Npc> npcs
}
