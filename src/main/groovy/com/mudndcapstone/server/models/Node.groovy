package com.mudndcapstone.server.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.id.UuidStrategy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

abstract class Node {
    @Id @GeneratedValue(strategy = UuidStrategy) String identifier
    @CreatedDate @JsonProperty("created_on") Date createdOn
    @LastModifiedDate @JsonProperty("last_modified_on") Date lastModifiedOn
}
