package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
class Combat extends Node {
    boolean running

    @Relationship(type = "NEXT_COMBAT", direction = Relationship.OUTGOING)
    Combat nextCombat

    @Relationship(type = "HAS_COMBAT_LIST", direction = Relationship.INCOMING)
    Session session

    @Relationship(type = "INVOLVES_ENEMY", direction = Relationship.OUTGOING)
    Set<Enemy> enemies
}
