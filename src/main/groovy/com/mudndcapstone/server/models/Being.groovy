package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

abstract class Being {
    @Id @GeneratedValue Long id
    String name
    @CreatedDate Date createdAt
    @LastModifiedDate Date modifiedAt
}
