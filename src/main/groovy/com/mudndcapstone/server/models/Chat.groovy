package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

@NodeEntity
class Chat {
    Long id
    @Id @GeneratedValue String identifier
    List<String> log
    String note
    @CreatedDate Date createdAt
    @LastModifiedDate Date modifiedAt

    @Relationship(type = "HAS_CHAT_LOG", direction = Relationship.INCOMING)
    Session session
}
