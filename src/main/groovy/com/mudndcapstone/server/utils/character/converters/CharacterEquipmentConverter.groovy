package com.mudndcapstone.server.utils.character.converters

import com.mudndcapstone.server.utils.character.CharacterEquipment
import org.neo4j.ogm.typeconversion.AttributeConverter

class CharacterEquipmentConverter implements AttributeConverter<List<CharacterEquipment>, List<String>> {

    @Override
    List<String> toGraphProperty(List<CharacterEquipment> equipments) {
        equipments.collect { it.name }
    }

    @Override
    List<CharacterEquipment> toEntityAttribute(List<String> strings) {
        strings.collect { new CharacterEquipment(it) }
    }

}
