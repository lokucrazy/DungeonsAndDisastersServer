package com.mudndcapstone.server.utils

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.Max
import javax.validation.constraints.Min

class BeingAbilities {
    @Min(value = 1L, message = Exceptions.ABILITY_SCORE_MIN_EXCEPTION)
    @Max(value = 30L, message = Exceptions.ABILITY_SCORE_MAX_EXCEPTION)
    int strength

    @Min(value = 1L, message = Exceptions.ABILITY_SCORE_MIN_EXCEPTION)
    @Max(value = 30L, message = Exceptions.ABILITY_SCORE_MAX_EXCEPTION)
    int dexterity

    @Min(value = 1L, message = Exceptions.ABILITY_SCORE_MIN_EXCEPTION)
    @Max(value = 30L, message = Exceptions.ABILITY_SCORE_MAX_EXCEPTION)
    int constitution

    @Min(value = 1L, message = Exceptions.ABILITY_SCORE_MIN_EXCEPTION)
    @Max(value = 30L, message = Exceptions.ABILITY_SCORE_MAX_EXCEPTION)
    int intelligence

    @Min(value = 1L, message = Exceptions.ABILITY_SCORE_MIN_EXCEPTION)
    @Max(value = 30L, message = Exceptions.ABILITY_SCORE_MAX_EXCEPTION)
    int wisdom

    @Min(value = 1L, message = Exceptions.ABILITY_SCORE_MIN_EXCEPTION)
    @Max(value = 30L, message = Exceptions.ABILITY_SCORE_MAX_EXCEPTION)
    int charisma

    @Min(value = -5L, message = Exceptions.ABILITY_MODIFIER_MIN_EXCEPTION)
    @Max(value = 10L, message = Exceptions.ABILITY_MODIFIER_MAX_EXCEPTION)
    @JsonProperty("strength_modifier") int strengthModifier

    @Min(value = -5L, message = Exceptions.ABILITY_MODIFIER_MIN_EXCEPTION)
    @Max(value = 10L, message = Exceptions.ABILITY_MODIFIER_MAX_EXCEPTION)
    @JsonProperty("dexterity_modifier") int dexterityModifier

    @Min(value = -5L, message = Exceptions.ABILITY_MODIFIER_MIN_EXCEPTION)
    @Max(value = 10L, message = Exceptions.ABILITY_MODIFIER_MAX_EXCEPTION)
    @JsonProperty("constitution_modifier") int constitutionModifier

    @Min(value = -5L, message = Exceptions.ABILITY_MODIFIER_MIN_EXCEPTION)
    @Max(value = 10L, message = Exceptions.ABILITY_MODIFIER_MAX_EXCEPTION)
    @JsonProperty("intelligence_modifier") int intelligenceModifier

    @Min(value = -5L, message = Exceptions.ABILITY_MODIFIER_MIN_EXCEPTION)
    @Max(value = 10L, message = Exceptions.ABILITY_MODIFIER_MAX_EXCEPTION)
    @JsonProperty("wisdom_modifier") int wisdomModifier

    @Min(value = -5L, message = Exceptions.ABILITY_MODIFIER_MIN_EXCEPTION)
    @Max(value = 10L, message = Exceptions.ABILITY_MODIFIER_MAX_EXCEPTION)
    @JsonProperty("charisma_modifier") int charismaModifier

    BeingAbilities() {}
    BeingAbilities(int s, int d, int c, int i, int w, int ch, int sm, int dm, int cm, int im, int wm, int chm) {
        strength = s
        dexterity = d
        constitution = c
        intelligence = i
        wisdom = w
        charisma = ch
        strengthModifier = sm
        dexterityModifier = dm
        constitutionModifier = cm
        intelligenceModifier = im
        wisdomModifier = wm
        charismaModifier = chm
    }

}
