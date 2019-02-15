package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
class Combat {
    @Id @GeneratedValue Long id

    @Relationship(type="NEXTCOMBAT", direction=Relationship.OUTGOING)
    Combat combat

    @Relationship(type="HASENEMY", direction=Relationship.OUTGOING)
    Set<Enemy> enemies
}
