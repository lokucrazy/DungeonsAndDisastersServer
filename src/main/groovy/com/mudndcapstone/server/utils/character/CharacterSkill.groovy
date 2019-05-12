package com.mudndcapstone.server.utils.character

import com.mudndcapstone.server.utils.character.enums.Skills

class CharacterSkill extends BaseSkill {
    Skills type

    CharacterSkill() {}
    CharacterSkill(Skills t, int v, int m, boolean tr) {
        type = t
        value = v
        modifier = m
        trained = tr
    }
}
