package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.NPC
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface NPCRepository extends Neo4jRepository<NPC, String> {
}