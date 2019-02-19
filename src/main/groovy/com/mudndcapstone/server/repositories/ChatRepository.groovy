package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Chat
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRepository extends Neo4jRepository<Chat, Long> {
}