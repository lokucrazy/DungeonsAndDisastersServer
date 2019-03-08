package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.id.UuidStrategy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

abstract class Being {
    @Id @GeneratedValue(strategy = UuidStrategy) Long id
    String name
    @CreatedDate Date createdAt
    @LastModifiedDate Date modifiedAt
}
