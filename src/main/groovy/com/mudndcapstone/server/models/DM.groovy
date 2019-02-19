package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.Relationship

class DM extends User {
    @Relationship(type = "HAS_DM", direction = Relationship.INCOMING)
    Set<Session> dmSessions

    @Relationship(type = "CONTROLS", direction = Relationship.OUTGOING)
    Set<NPC> npcs
}
