package com.mudndcapstone.server.utils.character.converters

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.mudndcapstone.server.utils.character.CharacterAttack
import org.neo4j.ogm.typeconversion.AttributeConverter

class CharacterAttackConverter implements AttributeConverter<List<CharacterAttack>, List<String>> {

    @Override
    List<String> toGraphProperty(List<CharacterAttack> attacks) {
        ObjectWriter jsonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter()
        attacks.collect { jsonWriter.writeValueAsString(it) }
    }

    @Override
    List<CharacterAttack> toEntityAttribute(List<String> strings) {
        ObjectMapper jsonReader = new ObjectMapper()
        strings.collect { jsonReader.readValue(it, CharacterAttack) }
    }

}
