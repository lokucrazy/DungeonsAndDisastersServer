package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import org.neo4j.ogm.id.UuidStrategy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

@NodeEntity
class Combat {
    @Id @GeneratedValue(strategy = UuidStrategy) Long id
    @CreatedDate Date createdAt
    @LastModifiedDate Date modifiedAt
    Combat previousCombat

    @Relationship(type = "HAS_COMBAT_LOG", direction = Relationship.INCOMING)
    Session session

    @Relationship(type = "INVOLVES_ENEMY", direction = Relationship.OUTGOING)
    Set<Enemy> enemies
}
