package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

import javax.validation.constraints.NotNull

@NodeEntity
class User {
    @Id @GeneratedValue Long id
    @NotNull String username
    @NotNull String password
    Date birthdate
    List<String> notes
    @CreatedDate Date createdAt
    @LastModifiedDate Date modifiedAt

    @Relationship(type = "CREATED_CHAR", direction = Relationship.OUTGOING)
    Set<Character> characters

    @Relationship(type = "HAS_PLAYER", direction = Relationship.INCOMING)
    Set<Session> sessions
}