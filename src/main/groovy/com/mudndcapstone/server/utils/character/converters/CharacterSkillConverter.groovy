package com.mudndcapstone.server.utils.character.converters

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.mudndcapstone.server.utils.character.CharacterSkill
import org.neo4j.ogm.typeconversion.AttributeConverter

class CharacterSkillConverter implements AttributeConverter<List<CharacterSkill>, List<String>> {

    @Override
    List<String> toGraphProperty(List<CharacterSkill> skills) {
        ObjectWriter jsonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter()
        skills.collect { jsonWriter.writeValueAsString(it) }
    }

    @Override
    List<CharacterSkill> toEntityAttribute(List<String> strings) {
        ObjectMapper jsonReader = new ObjectMapper()
        strings.collect { jsonReader.readValue(it, CharacterSkill) }
    }

}
