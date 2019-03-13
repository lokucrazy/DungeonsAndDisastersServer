package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Session
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionRepository extends Neo4jRepository<Session, String> {
}