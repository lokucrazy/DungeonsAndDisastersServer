package com.mudndcapstone.server.models

import com.mudndcapstone.server.utils.BeingAbilities
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
class NPC extends Being {
    int health
    boolean isAlive
    String initialLocation /* maybe move this into a Location class */
    BeingAbilities abilities

    @Relationship(type = "HAS_NPC", direction = Relationship.INCOMING)
    Session session

    @Relationship(type = "CONTROLS", direction = Relationship.INCOMING)
    User dm
}
