package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Combat
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface CombatRepository extends Neo4jRepository<Combat, String> {

    @Query("""MATCH (c:Combat {identifier:{0}})
              OPTIONAL MATCH (c)-[:NEXT_COMBAT]->(c1:Combat)
              CALL apoc.when(c1 IS NOT NULL, 
                "RETURN last(collect(c1)) AS result","RETURN c AS result",{c1:c1,c:c}) YIELD value
              WITH value.result as nodeResult
              OPTIONAL MATCH (nodeResult)<-[r:HAS_COMBAT_LIST]-(i)
              RETURN nodeResult,r,i""")
    Optional<Combat> findLastNodeInPath(String id)
}