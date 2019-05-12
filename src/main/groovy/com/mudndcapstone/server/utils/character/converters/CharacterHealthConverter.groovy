package com.mudndcapstone.server.utils.character.converters

import com.mudndcapstone.server.utils.character.CharacterHealth
import org.neo4j.ogm.typeconversion.CompositeAttributeConverter

class CharacterHealthConverter implements CompositeAttributeConverter<CharacterHealth> {

    @Override
    Map<String, ?> toGraphProperties(CharacterHealth health) {
        Map<String, Integer> properties = [:]

        if (health) {
            properties.put("current_hit_points", health.currentHitPoints)
            properties.put("maximum_hit_points", health.maximumHitPoints)
            properties.put("temporary_hit_points", health.temporaryHitPoints)
            properties.put("hit_dice", health.hitDice)
            properties.put("success_death_saves", health.successDeathSaves)
            properties.put("failure_death_saves", health.failureDeathSaves)
        }

        properties
    }

    @Override
    CharacterHealth toEntityAttribute(Map<String, ?> map) {
        Integer currentHitPoints = (Integer) map.get("current_hit_points")
        Integer maximumHitPoints = (Integer) map.get("maximum_hit_points")
        Integer temporaryHitPoints = (Integer) map.get("temporary_hit_points")
        Integer hitDice = (Integer) map.get("hit_dice")
        Integer successDeathSaves = (Integer) map.get("success_death_saves")
        Integer failureDeathSaves = (Integer) map.get("failure_death_saves")

        // Check all values present
        List<Integer> properties = [
                currentHitPoints, maximumHitPoints, temporaryHitPoints, hitDice, successDeathSaves, failureDeathSaves
        ]

        properties.contains(null) ?
                null :
                new CharacterHealth(currentHitPoints, maximumHitPoints, temporaryHitPoints, hitDice, successDeathSaves, failureDeathSaves)
    }

}
