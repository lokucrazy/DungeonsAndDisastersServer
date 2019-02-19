package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.History
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface HistoryRepository extends Neo4jRepository<History, Long> {
}