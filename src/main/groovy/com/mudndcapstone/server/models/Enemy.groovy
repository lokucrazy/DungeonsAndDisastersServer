package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
class Enemy extends NPC {
    @Relationship(type = "INVOLVES_ENEMY", direction = Relationship.INCOMING)
    Combat combat
}
