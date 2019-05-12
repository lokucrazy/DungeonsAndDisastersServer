package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

/* might not need this class, can possibly just grab from static API */
class EnemyDto extends NPCDto {
    @JsonProperty("combat_id") String combatId
}
