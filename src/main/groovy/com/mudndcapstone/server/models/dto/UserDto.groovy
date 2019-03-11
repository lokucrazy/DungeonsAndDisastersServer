package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class UserDto extends EntityDto {
    @NotNull String username
    @NotNull String password
    Date birthdate
    List<String> notes
    @JsonProperty(value = "character_ids") List<String> characterIds
    @JsonProperty(value = "session_ids") List<String> sessionIds
    @JsonProperty(value = "dm_session_ids") List<String> dmSessionIds
    @JsonProperty(value = "npc_ids") List<String> npcIds
}
