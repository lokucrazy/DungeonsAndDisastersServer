package com.mudndcapstone.server.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.mudndcapstone.server.utils.BeingAbilities
import com.mudndcapstone.server.utils.character.*
import com.mudndcapstone.server.utils.character.converters.*
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import org.neo4j.ogm.annotation.Required
import org.neo4j.ogm.annotation.typeconversion.Convert
import org.springframework.data.annotation.CreatedBy

@NodeEntity
class Character extends Being {
    @Required int level
    @Required int experience
    @Required @JsonProperty("class") CharacterClass characterClass
    @Required String background
    @Required CharacterRace race
    @Required CharacterAlignment alignment
    @Convert(BeingAbilitiesConverter) BeingAbilities abilities
    @Convert(CharacterSavingThrowConverter) List<CharacterSavingThrow> savingThrows
    @Convert(CharacterSkillConverter) List<CharacterSkill> skills
    int inspiration
    int proficiency
    int perception
    @JsonProperty("armor_class") int armorClass
    int initiative
    int speed
    @Convert(CharacterHealthConverter) CharacterHealth health
    @Convert(CharacterAttackConverter) List<CharacterAttack> attacks
    @Convert(CharacterEquipmentConverter) List<CharacterEquipment> equipment
    @Convert(CharacterMoniesConverter) CharacterMonies monies
    List<String> proficiencies
    List<String> languages
    List<String> feats

    @Required
    @Relationship(type = "CREATED_CHARACTER", direction = Relationship.INCOMING)
    @CreatedBy User user

    @Relationship(type = "HAS_CHARACTER", direction = Relationship.INCOMING)
    Set<Session> sessions
}
