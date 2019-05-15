package com.mudndcapstone.server.utils.character

import com.fasterxml.jackson.annotation.JsonProperty

enum CharacterRace {
    @JsonProperty("dragonborn") DRAGONBORN,
    @JsonProperty("dwarf") DWARF,
    @JsonProperty("elf") ELF,
    @JsonProperty("gnome") GNOME,
    @JsonProperty("half elf") HALF_ELF,
    @JsonProperty("half orc") HALF_ORC,
    @JsonProperty("halfling") HALFLING,
    @JsonProperty("human") HUMAN,
    @JsonProperty("tiefling") TIEFLING
}