package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Enemy
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface EnemyRepository extends Neo4jRepository<Enemy, String> {
}