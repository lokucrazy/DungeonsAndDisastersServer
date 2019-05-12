package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import org.neo4j.ogm.annotation.Required

import javax.validation.constraints.NotNull

@NodeEntity
class Enemy extends NPC {
    @NotNull
    @Relationship(type = "INVOLVES_ENEMY", direction = Relationship.INCOMING)
    Combat combat
}
