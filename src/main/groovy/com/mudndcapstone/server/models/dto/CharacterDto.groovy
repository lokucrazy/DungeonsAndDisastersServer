package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.mudndcapstone.server.utils.BeingAbilities
import com.mudndcapstone.server.utils.character.CharacterAlignment
import com.mudndcapstone.server.utils.character.CharacterAttack
import com.mudndcapstone.server.utils.character.CharacterClass
import com.mudndcapstone.server.utils.character.CharacterEquipment
import com.mudndcapstone.server.utils.character.CharacterHealth
import com.mudndcapstone.server.utils.character.CharacterMonies
import com.mudndcapstone.server.utils.character.CharacterRace
import com.mudndcapstone.server.utils.character.CharacterSavingThrow
import com.mudndcapstone.server.utils.character.CharacterSkill

import javax.validation.Valid
import javax.validation.constraints.NotNull

class CharacterDto extends BeingDto {
    @JsonProperty("class") @NotNull CharacterClass characterClass
    @NotNull String background
    @NotNull CharacterRace race
    @NotNull CharacterAlignment alignment
    @NotNull int level
    @NotNull int experience
    @NotNull @Valid BeingAbilities abilities
    List<CharacterSavingThrow> savingThrows
    List<CharacterSkill> skills
    int inspiration
    int proficiency
    int perception
    @JsonProperty("armor_class") int armorClass
    int initiative
    int speed
    @NotNull @Valid CharacterHealth health
    List<CharacterAttack> attacks
    List<CharacterEquipment> equipment
    CharacterMonies monies
    List<String> proficiencies
    List<String> languages
    List<String> feats
    @JsonProperty("user_id") String userId
    @JsonProperty("session_ids") Set<String> sessionIds
}
