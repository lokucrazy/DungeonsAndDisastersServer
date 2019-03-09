package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

@NodeEntity
class Map {
    Long id
    @Id @GeneratedValue String identifier
    @CreatedDate Date createdAt
    @LastModifiedDate Date modifiedAt
    List<String> images

    @Relationship(type = "HAS_MAP_LIST", direction = Relationship.INCOMING)
    Session session
}
