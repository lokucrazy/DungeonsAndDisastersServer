package com.mudndcapstone.server.utils.character.enums

import com.fasterxml.jackson.annotation.JsonProperty

enum SavingThrow {
    @JsonProperty("strength") STRENGTH,
    @JsonProperty("dexterity") DEXTERITY,
    @JsonProperty("constitution") CONSTITUTION,
    @JsonProperty("intelligence") INTELLIGENCE,
    @JsonProperty("wisdom") WISDOM,
    @JsonProperty("charisma") CHARISMA
}
