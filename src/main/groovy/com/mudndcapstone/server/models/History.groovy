package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

@NodeEntity
class History {
    @Id @GeneratedValue Long id
    List<String> nonCombatLog
    List<String> combatLog
    @CreatedDate Date createdAt
    @LastModifiedDate Date modifiedAt
    Date dateEnded

    @Relationship(type = "HAS_PREVIOUS_SESSION", direction = Relationship.OUTGOING)
    History history
}
