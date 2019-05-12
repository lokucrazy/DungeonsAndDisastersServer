package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class CombatDto extends EntityDto {
    @JsonProperty("next_combat_id") String nextCombatId
    @JsonProperty("session_id") @NotNull String sessionId
    @JsonProperty("enemy_ids") Set<String> enemyIds
}
