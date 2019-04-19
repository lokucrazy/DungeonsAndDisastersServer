package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Session
import org.neo4j.ogm.model.Result
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionRepository extends Neo4jRepository<Session, String> {
    @Query("""MATCH (o:Session {identifier:{0}})
              MATCH (n:Session {identifier:{1}})
              MATCH (o)-[r]->() WHERE NOT type(r)='HAS_PREVIOUS_SESSION'
              CALL apoc.refactor.from(r,n) YIELD input, output
              MATCH (n)-[r1]->(i)
              RETURN n,r1,i""")
    Optional<Session> refactorRelationships(String oldSessionId, String newSessionId)
}