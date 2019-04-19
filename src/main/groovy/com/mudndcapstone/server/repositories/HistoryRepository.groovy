package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.History
import org.neo4j.ogm.model.Result
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface HistoryRepository extends Neo4jRepository<History, String> {

    @Query("""MATCH (h {identifier:{0}})
              REMOVE h:Session
              WITH h
              OPTIONAL MATCH (h)-[r]->()
              RETURN h,r""")
    Optional<History> removeSessionLabel(String sessionId)
}