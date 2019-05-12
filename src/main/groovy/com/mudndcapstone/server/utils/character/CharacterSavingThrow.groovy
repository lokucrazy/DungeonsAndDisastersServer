package com.mudndcapstone.server.utils.character

import com.mudndcapstone.server.utils.character.enums.SavingThrow

class CharacterSavingThrow extends BaseSkill {
    SavingThrow type

    CharacterSavingThrow(SavingThrow t, int v, int m, boolean tr) {
        type = t
        value = v
        modifier = m
        trained = tr
    }
}
