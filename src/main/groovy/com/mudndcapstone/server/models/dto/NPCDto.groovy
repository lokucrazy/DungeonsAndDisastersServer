package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.mudndcapstone.server.utils.BeingAbilities

import javax.validation.constraints.NotNull

class NPCDto extends BeingDto {
    int health
    @JsonProperty("is_alive") boolean isAlive
    @JsonProperty("initial_location") String initialLocation /* maybe move this into a Location class */
    BeingAbilities abilities
    @JsonProperty("session_id") String sessionId
    @JsonProperty("dm_id") @NotNull String dmId
}
