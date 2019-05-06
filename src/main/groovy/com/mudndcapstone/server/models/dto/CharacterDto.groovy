package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.mudndcapstone.server.utils.BeingAbilities
import com.mudndcapstone.server.utils.character.CharacterAlignment
import com.mudndcapstone.server.utils.character.CharacterClass
import com.mudndcapstone.server.utils.character.CharacterRace

import javax.validation.constraints.NotNull

class CharacterDto extends BeingDto {
    @JsonProperty(value = "user_id") String userId
    @NotNull @JsonProperty(value = "class") CharacterClass characterClass
    @NotNull String background
    @NotNull CharacterRace race
    @NotNull CharacterAlignment alignment
    int level
    int experience
    BeingAbilities abilities
    @JsonProperty(value = "session_ids") Set<String> sessionIds
}
