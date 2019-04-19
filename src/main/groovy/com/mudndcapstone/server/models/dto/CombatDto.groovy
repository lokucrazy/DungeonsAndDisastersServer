package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class CombatDto extends EntityDto {
    @JsonProperty(value = "next_combat_id") String nextCombatId
    @JsonProperty(value = "session_id") @NotNull String sessionId
    @JsonProperty(value = "enemy_ids") Set<String> enemyIds
}
