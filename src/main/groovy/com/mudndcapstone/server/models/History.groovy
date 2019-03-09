package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
class History extends Node {
    List<String> nonCombatLog
    List<String> combatLog
    Date dateEnded

    @Relationship(type = "HAS_PREVIOUS_SESSION", direction = Relationship.OUTGOING)
    History history /* might need to rename this to previousHistory */
}
