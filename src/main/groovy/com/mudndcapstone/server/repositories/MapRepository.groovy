package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Map
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface MapRepository extends Neo4jRepository<Map, String> {
}