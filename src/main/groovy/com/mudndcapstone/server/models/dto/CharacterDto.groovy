package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.mudndcapstone.server.utils.BeingAbilities
import com.mudndcapstone.server.utils.CharacterAlignment
import com.mudndcapstone.server.utils.CharacterClass
import com.mudndcapstone.server.utils.CharacterRace

import javax.validation.constraints.NotNull

class CharacterDto extends BeingDto {
    @NotNull @JsonProperty(value = "user_id") Long userId
    @NotNull @JsonProperty(value = "class") CharacterClass characterClass
    @NotNull String background
    @NotNull CharacterRace race
    @NotNull CharacterAlignment alignment
    int level
    int experience
    BeingAbilities abilities
    @JsonProperty(value = "session_ids") List<Long> sessionIds
}
