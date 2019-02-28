package com.mudndcapstone.server.models

import com.mudndcapstone.server.utils.CharacterAlignment
import com.mudndcapstone.server.utils.CharacterClass
import com.mudndcapstone.server.utils.CharacterRace
import org.neo4j.ogm.annotation.Property

class CharacterRequest extends Being {
    @Property(name = "class") CharacterClass characterClass
    String background
    CharacterRace race
    CharacterAlignment alignment
}
