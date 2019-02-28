package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

@NodeEntity
class User extends UserRequest {
    @Id @GeneratedValue Long id
    List<String> notes
    @CreatedDate Date createdAt
    @LastModifiedDate Date modifiedAt

    @Relationship(type = "CREATED_CHAR", direction = Relationship.OUTGOING)
    Set<Character> characters

    @Relationship(type = "HAS_PLAYER", direction = Relationship.INCOMING)
    Set<Session> sessions

    static User from(UserRequest userRequest) {
        User user = new User()

        user.setUsername(userRequest.username)
        user.setPassword(userRequest.password)
        user.setBirthdate(userRequest.birthdate)

        user
    }
}