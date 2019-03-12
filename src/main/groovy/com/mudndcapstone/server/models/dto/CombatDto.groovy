package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

class CombatDto extends EntityDto {
    @JsonProperty(value = "prev_session_id") Long previousCombatId
    @JsonProperty(value = "session_id") Long sessionId
    @JsonProperty(value = "enemy_ids") Set<Long> enemyIds
}
