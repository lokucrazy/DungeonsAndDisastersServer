package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.mudndcapstone.server.utils.BeingAbilities
import com.mudndcapstone.server.utils.CharacterAlignment
import com.mudndcapstone.server.utils.CharacterClass
import com.mudndcapstone.server.utils.CharacterRace

import javax.validation.constraints.NotNull

class CharacterDto extends BeingDto {
    @NotNull @JsonProperty(value = "user_id") String userId
    @NotNull @JsonProperty(value = "class") CharacterClass characterClass
    @NotNull String background
    @NotNull CharacterRace race
    @NotNull CharacterAlignment alignment
    @NotNull int level
    @NotNull int experience
    @NotNull BeingAbilities abilities
    @JsonProperty(value = "session_ids") Set<String> sessionIds
}
