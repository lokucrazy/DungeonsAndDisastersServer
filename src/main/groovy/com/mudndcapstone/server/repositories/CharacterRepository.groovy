package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Character
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface CharacterRepository extends Neo4jRepository<Character, Long> {
}
