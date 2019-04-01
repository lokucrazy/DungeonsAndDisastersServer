package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
class Combat extends Node {
    Combat previousCombat

    @Relationship(type = "HAS_COMBAT_LIST", direction = Relationship.INCOMING)
    Session session

    @Relationship(type = "INVOLVES_ENEMY", direction = Relationship.OUTGOING)
    Set<Enemy> enemies
}
