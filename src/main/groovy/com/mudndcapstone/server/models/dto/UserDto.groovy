package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class UserDto extends EntityDto {
    @NotNull String username
    @NotNull String password
    Date birthdate
    List<String> notes
    @JsonProperty(value = "character_ids") Set<Long> characterIds
    @JsonProperty(value = "session_ids") Set<Long> sessionIds
    @JsonProperty(value = "dm_session_ids") Set<Long> dmSessionIds
    @JsonProperty(value = "npc_ids") Set<Long> npcIds
}
