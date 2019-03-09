package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

abstract class Being {
    Long id
    @Id @GeneratedValue String identifier
    String name
    @CreatedDate Date createdAt
    @LastModifiedDate Date modifiedAt
}
