package com.mudndcapstone.server.utils.character.converters

import com.mudndcapstone.server.utils.BeingAbilities
import org.neo4j.ogm.typeconversion.CompositeAttributeConverter

class BeingAbilitiesConverter implements CompositeAttributeConverter<BeingAbilities> {

    @Override
    Map<String, ?> toGraphProperties(BeingAbilities abilities) {
        Map<String, Integer> properties = [:]

        if (abilities) {
            properties.put("strength", abilities.strength)
            properties.put("dexterity", abilities.dexterity)
            properties.put("constitution", abilities.constitution)
            properties.put("intelligence", abilities.intelligence)
            properties.put("wisdom", abilities.wisdom)
            properties.put("charisma", abilities.charisma)

            properties.put("strength_modifier", abilities.strengthModifier)
            properties.put("dexterity_modifier", abilities.dexterityModifier)
            properties.put("constitution_modifier", abilities.constitutionModifier)
            properties.put("intelligence_modifier", abilities.intelligenceModifier)
            properties.put("wisdom_modifier", abilities.wisdomModifier)
            properties.put("charisma_modifier", abilities.charismaModifier)
        }

        properties
    }

    @Override
    BeingAbilities toEntityAttribute(Map<String, ?> map) {
        Integer strength = (Integer) map.get("strength")
        Integer dexterity = (Integer) map.get("dexterity")
        Integer constitution = (Integer) map.get("constitution")
        Integer intelligence = (Integer) map.get("intelligence")
        Integer wisdom = (Integer) map.get("wisdom")
        Integer charisma = (Integer) map.get("charisma")

        Integer strengthModifier = (Integer) map.get("strength_modifier")
        Integer dexterityModifier = (Integer) map.get("dexterity_modifier")
        Integer constitutionModifier = (Integer) map.get("constitution_modifier")
        Integer intelligenceModifier = (Integer) map.get("intelligence_modifier")
        Integer wisdomModifier = (Integer) map.get("wisdom_modifier")
        Integer charismaModifier = (Integer) map.get("charisma_modifier")

        // Check all values present
        List<Integer> properties = [
                strength, dexterity, constitution, intelligence, wisdom, charisma, strengthModifier, dexterityModifier,
                constitutionModifier, intelligenceModifier, wisdomModifier, charismaModifier
        ]

        properties.contains(null) ?
                null :
                new BeingAbilities(strength, dexterity, constitution, intelligence, wisdom, charisma, strengthModifier,
                dexterityModifier, constitutionModifier, intelligenceModifier, wisdomModifier, charismaModifier)
    }
}
