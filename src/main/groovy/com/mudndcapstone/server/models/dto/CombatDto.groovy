package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

class CombatDto extends EntityDto {
    @JsonProperty(value = "next_combat_id") String nextCombatId
    @JsonProperty(value = "session_id") String sessionId
    @JsonProperty(value = "enemy_ids") Set<String> enemyIds
}
