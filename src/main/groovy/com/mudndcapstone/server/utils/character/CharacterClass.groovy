package com.mudndcapstone.server.utils.character

import com.fasterxml.jackson.annotation.JsonProperty

enum CharacterClass {
    @JsonProperty("barbarian") BARBARIAN,
    @JsonProperty("bard") BARD,
    @JsonProperty("cleric") CLERIC,
    @JsonProperty("druid") DRUID,
    @JsonProperty("fighter") FIGHTER,
    @JsonProperty("monk") MONK,
    @JsonProperty("paladin") PALADIN,
    @JsonProperty("ranger") RANGER,
    @JsonProperty("rogue") ROGUE,
    @JsonProperty("sorcerer") SORCERER,
    @JsonProperty("warlock") WARLOCK,
    @JsonProperty("wizard") WIZARD
}