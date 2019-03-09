package com.mudndcapstone.server.models.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.mudndcapstone.server.utils.CharacterAlignment
import com.mudndcapstone.server.utils.CharacterClass
import com.mudndcapstone.server.utils.CharacterRace

import javax.validation.constraints.NotNull

class CharacterRequest {
    @NotNull @JsonProperty(value = "user_id") Long userId
    @NotNull String name
    @NotNull @JsonProperty(value = "class") CharacterClass characterClass
    @NotNull String background
    @NotNull CharacterRace race
    @NotNull CharacterAlignment alignment
}

