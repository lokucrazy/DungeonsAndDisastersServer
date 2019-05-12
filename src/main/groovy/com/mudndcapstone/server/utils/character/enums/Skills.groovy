package com.mudndcapstone.server.utils.character.enums

import com.fasterxml.jackson.annotation.JsonProperty

enum Skills {
    @JsonProperty("athletics") ATHLETICS,
    @JsonProperty("acrobatics") ACROBATICS,
    @JsonProperty("sleight of hand") SLEIGHT_OF_HAND,
    @JsonProperty("stealth") STEALTH,
    @JsonProperty("arcana") ARCANA,
    @JsonProperty("history") HISTORY,
    @JsonProperty("investigation") INVESTIGATION,
    @JsonProperty("nature") NATURE,
    @JsonProperty("religion") RELIGION,
    @JsonProperty("animal handling") ANIMAL_HANDLING,
    @JsonProperty("insight") INSIGHT,
    @JsonProperty("medicine") MEDICINE,
    @JsonProperty("perception") PERCEPTION,
    @JsonProperty("survival") SURVIVAL,
    @JsonProperty("deception") DECEPTION,
    @JsonProperty("intimidation") INTIMIDATION,
    @JsonProperty("performance") PERFORMANCE,
    @JsonProperty("persuasion") PERSUASION
}
