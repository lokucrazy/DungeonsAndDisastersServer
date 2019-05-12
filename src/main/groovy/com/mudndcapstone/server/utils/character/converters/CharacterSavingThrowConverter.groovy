package com.mudndcapstone.server.utils.character.converters

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.mudndcapstone.server.utils.character.CharacterSavingThrow
import org.neo4j.ogm.typeconversion.AttributeConverter

class CharacterSavingThrowConverter implements AttributeConverter<List<CharacterSavingThrow>, List<String>> {

    @Override
    List<String> toGraphProperty(List<CharacterSavingThrow> savingThrows) {
        ObjectWriter jsonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter()
        savingThrows.collect { jsonWriter.writeValueAsString(it) }
    }

    @Override
    List<CharacterSavingThrow> toEntityAttribute(List<String> strings) {
        ObjectMapper jsonReader = new ObjectMapper()
        strings.collect { jsonReader.readValue(it, CharacterSavingThrow) }
    }

}
