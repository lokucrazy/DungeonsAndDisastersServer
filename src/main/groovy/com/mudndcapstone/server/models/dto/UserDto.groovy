package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class UserDto extends EntityDto {
    @NotNull String username
    @NotNull String password
    Date birthdate
    List<String> notes
    @JsonProperty("character_ids") Set<String> characterIds
    @JsonProperty("session_ids") Set<String> sessionIds
    @JsonProperty("dm_session_ids") Set<String> dmSessionIds
    @JsonProperty("npc_ids") Set<String> npcIds
}
