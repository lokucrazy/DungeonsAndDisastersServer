package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

class Node {
    Long id
    @Id @GeneratedValue Long identifier
    @CreatedDate Date createdAt
    @LastModifiedDate Date modifiedAt
}
