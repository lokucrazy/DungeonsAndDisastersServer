package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class EnemyDto extends NPCDto {
    @JsonProperty("combat_id") @NotNull String combatId
}
