package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Combat
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface CombatRepository extends Neo4jRepository<Combat, Long> {
}