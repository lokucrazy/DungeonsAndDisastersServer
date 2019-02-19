package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.User
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends Neo4jRepository<User, Long> {
}