package com.mudndcapstone.server.utils.character

class CharacterAttack {
    String name
    int damage
    int bonus

    CharacterAttack() {}
    CharacterAttack(String n, int d, int b) {
        name = n
        damage = d
        bonus = b
    }
}
