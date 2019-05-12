package com.mudndcapstone.server.utils.character

import com.fasterxml.jackson.annotation.JsonProperty

enum CharacterAlignment {
    @JsonProperty("lawful good") LAWFUL_GOOD,
    @JsonProperty("neutral good") NEUTRAL_GOOD,
    @JsonProperty("chaotic good") CHAOTIC_GOOD,
    @JsonProperty("lawful neutral") LAWFUL_NEUTRAL,
    @JsonProperty("neutral") NEUTRAL,
    @JsonProperty("chaotic neutral") CHAOTIC_NEUTRAL,
    @JsonProperty("lawful evil") LAWFUL_EVIL,
    @JsonProperty("neutral evil") NEUTRAL_EVIL,
    @JsonProperty("chaotic evil") CHAOTIC_EVIL
}