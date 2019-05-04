package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.mudndcapstone.server.utils.BeingAbilities

import javax.validation.constraints.NotNull

/* might not need this class, can possibly just grab from static API */
class NPCDto extends BeingDto {
    int health
    @JsonProperty(value = "is_alive") boolean isAlive
    @JsonProperty(value = "initial_location") String initialLocation /* maybe move this into a Location class */
    BeingAbilities abilities
    @JsonProperty(value = "session_id") String sessionId
    @JsonProperty(value = "dm_id") @NotNull String dmId
}
