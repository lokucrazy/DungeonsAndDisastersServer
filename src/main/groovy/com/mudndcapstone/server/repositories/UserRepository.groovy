package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.User
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends Neo4jRepository<User, String> {
    Optional<User> findByUsername(String username)

    @Query("MATCH (dm)<-[:HAS_DM]-() RETURN dm")
    List<User> findAllDMs()

    @Query("MATCH (user:User) WHERE user.identifier={0} RETURN user.username")
    Optional<String> findUsernameById(String identifier)
}