package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

class CombatDto extends EntityDto {
    @JsonProperty(value = "prev_session_id") String previousCombatId
    @JsonProperty(value = "session_id") String sessionId
    @JsonProperty(value = "enemy_ids") List<String> enemyIds
}
