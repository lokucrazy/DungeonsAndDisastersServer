package com.mudndcapstone.server.utils

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.Enemy
import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.models.NPC
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.request.CharacterRequest
import com.mudndcapstone.server.models.request.ChatRequest
import com.mudndcapstone.server.models.request.CombatRequest
import com.mudndcapstone.server.models.request.EnemyRequest
import com.mudndcapstone.server.models.request.MapRequest
import com.mudndcapstone.server.models.request.NPCRequest
import com.mudndcapstone.server.models.request.SessionRequest
import com.mudndcapstone.server.models.request.UserRequest

class ModelBuilder {

    static Character buildCharacterFrom(CharacterRequest characterRequest) {
        Character character = new Character()

        character.setCharacterClass(characterRequest.characterClass)
        character.setBackground(characterRequest.background)
        character.setRace(characterRequest.race)
        character.setAlignment(characterRequest.alignment)

        character
    }

    static Chat buildChatFrom(ChatRequest chatRequest) {
        Chat chat = new Chat()

        chat
    }

    static Combat buildCombatFrom(CombatRequest combatRequest) {
        Combat combat = new Combat()

        combat
    }

    static Enemy buildEnemyFrom(EnemyRequest enemyRequest) {
        Enemy enemy = new Enemy()

        enemy
    }

    static Map buildMapFrom(MapRequest mapRequest) {
        Map map = new Map()

        map
    }

    static NPC buildNPCFrom(NPCRequest npcRequest) {
        NPC npc = new NPC()

        npc
    }

    static Session buildSessionFrom(SessionRequest sessionRequest) {
        Session session = new Session()

        session
    }

    static User buildUserFrom(UserRequest userRequest) {
        User user = new User()

        user.setUsername(userRequest.username)
        user.setPassword(userRequest.password)
        user.setBirthdate(userRequest.birthdate)

        user
    }

}
