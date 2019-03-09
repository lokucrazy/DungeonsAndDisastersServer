package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.User
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends Neo4jRepository<User, Long> {
    @Query("MATCH (dm)<-[:HAS_DM]-() RETURN dm")
    Set<User> findAllDMs()

    @Query("MATCH (dm)<-[:HAS_DM]-() WHERE id(dm)={0} RETURN dm")
    Optional<User> findDMById(Long id)
}