package com.mudndcapstone.server.utils

import com.fasterxml.jackson.annotation.JsonProperty

class BeingAbilities {
    int strength
    int dexterity
    int constitution
    int intelligence
    int wisdom
    int charisma

    @JsonProperty("strength_modifier") int strengthModifier
    @JsonProperty("dexterity_modifier") int dexterityModifier
    @JsonProperty("constitution_modifier") int constitutionModifier
    @JsonProperty("intelligence_modifier") int intelligenceModifier
    @JsonProperty("wisdom_modifier") int wisdomModifier
    @JsonProperty("charisma_modifier") int charismaModifier
}
