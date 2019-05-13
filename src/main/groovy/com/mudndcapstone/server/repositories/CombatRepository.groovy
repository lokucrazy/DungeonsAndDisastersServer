package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Combat
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface CombatRepository extends Neo4jRepository<Combat, String> {
    @Query("""OPTIONAL MATCH (prev:Combat)-[:NEXT_COMBAT]->(current:Combat {identifier:{0}})
              RETURN prev.identifier""")
    Optional<String> findPreviousCombatId(String combatId)
}