package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Combat
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface CombatRepository extends Neo4jRepository<Combat, String> {
    @Query("""MATCH (prev:Combat)-[:NEXT_COMBAT]->(current:Combat {identifier:{0}})
              OPTIONAL MATCH (prev)-[r]->(i) WHERE NOT type(r)='NEXT_COMBAT'
              RETURN prev,r,i""")
    Optional<Combat> findPreviousCombat(String combatId)
}