package com.mudndcapstone.server.utils.character.converters

import com.mudndcapstone.server.utils.character.CharacterMonies
import org.neo4j.ogm.typeconversion.CompositeAttributeConverter

class CharacterMoniesConverter implements CompositeAttributeConverter<CharacterMonies> {

    @Override
    Map<String, ?> toGraphProperties(CharacterMonies monies) {
        Map<String, Integer> properties = [:]

        if (monies) {
            properties.put("copper", monies.copper)
            properties.put("silver", monies.silver)
            properties.put("electrum", monies.electrum)
            properties.put("gold", monies.gold)
            properties.put("platinum", monies.platinum)
        }

        properties
    }

    @Override
    CharacterMonies toEntityAttribute(Map<String, ?> map) {
        Integer copper = (Integer) map.get("copper")
        Integer silver = (Integer) map.get("silver")
        Integer electrum = (Integer) map.get("electrum")
        Integer gold = (Integer) map.get("gold")
        Integer platinum = (Integer) map.get("platinum")

        // Check all values present
        List<Integer> properties = [
                copper, silver, electrum, gold, platinum
        ]

        properties.contains(null) ?
                null :
                new CharacterMonies(copper, silver, electrum, gold, platinum)
    }

}
