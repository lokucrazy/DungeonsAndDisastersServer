# Dungeons And Disasters Server
### Spring Server for Senior Capstone Project 2019

A server for Dungeons and also a server for Dragons.

### Technologies
- [Java](https://www.java.com/en/)
- [Spring](https://spring.io)

### Routes

##### _Note: all routes prefixed with "/api/v1"_

##### Generic Routes
_X means the associated model_

_If there is a 404 for a generic route of a model, that means that route does not exist
or is overridden by a custom route_

| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |
| GET /X/{Xid} | Get X | | X |
| POST /X | Create X | Xdto | X |
| PUT /X/{Xid} | Update X | Xdto | X |
| DELETE /X/{Xid} | Delete X | | 204 |

##### Custom Routes

#### Session
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |
| GET /sessions/{sessionId}/characters | Get all Characters in a Session | | [CharacterDto] |
| GET /sessions/{sessionId}/chats | Get chat log of a Session | | [String] |
| GET /sessions/{sessionId}/maps | Get Map node of a Session | | MapDto |
| POST /sessions/{sessionId}/combats?insert | Create combat in a Session | CombatDto | CombatDto |
| PATCH /sessions/{sessionId}/state | Set Session state | SessionState | SessionDto |
| PUT /sessions/{sessionId}/characters/{characterId} | Connect Character to a Session | | SessionDto |
| PUT /sessions/{sessionId}/users/{userId} | Connect User to a Session | | SessionDto |

#### User (users, dms)
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |
| POST /login/{username}?password | Get User based on username and password | | UserDto |
| GET /users/{userId}/characters | Get all Characters in a User | | [CharacterDto] |
| POST /users/{userId}/characters | Create a Character | CharacterDto | CharacterDto |
| GET /users/{userId}/notes | Get all user's notes | | [String] |
| POST /users/{userId}/notes | Create a new note for a user | Messenger | UserDto |
| POST /dms/{dmId}/npcs | Create an NPC | NPCDto | NPCDto |

#### Being (npcs, enemies)
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |

#### Character
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |

##### Chat
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |
| PUT /chats/{chatId} | Add message to chat log | Messenger | [String] |

#### Combat
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |
| POST /combats/{combatId}/enemies | Create Enemy in a Combat | EnemyDto | EnemyDto |

#### Map
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |

#### History
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |

### Objects
_properties marked with_ ! _means they're required_

_properties marked with_ * _means they're used for creation_

##### UserDto
```
{
    "id": "",
!*  "username": "",
!*  "password": "",
!*  "birthdate": "YYYY-mm-dd",
    "notes": [],
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
    "character_ids": [],
    "session_ids": [],
    "dm_session_ids": [],
    "npc_ids": []
}
```

#### SessionDto
```
{
*   "id": "",
    "session_state": false,
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
!*  "dm_id": "",
    "non_combat_log": [],
    "combat_log": [],
    "date_ended": "YYYY-mm-dd HH:mm a",
    "history_id": "",
    "chat_id": "",
    "map_id": "",
    "combat_id": "",
    "npc_ids": [],
    "player_ids": [],
    "character_ids": []
}
```

#### SessionState
```
{
!   "running": false
}
```

#### HistoryDto
```
{
    "id": "",
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
    "non_combat_log": [],
    "combat_log": [],
    "date_ended": "",
    "history_id": ""
}
```

#### NPCDto
```
{
    "id": "",
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
!*  "name": "",
*   "health": 0,
    "is_alive": true,
    "initial_location": "",
*   "abilities": {},
*   "session_id": "",
*   "dm_id": ""
}
```

#### MapDto
```
{
    "id": "",
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
!   "session_id": "",
    "images": []
}
```

#### Messenger
```
{
!   "body": ""
}
```

#### EnemyDto
```
{
    "id": "",
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
!*  "name": "",
*   "health": 0,
    "is_alive": true,
    "initial_location": "",
*   "abilities": {},
*   "session_id": "",
*   "combat_id": ""
}
```

#### CombatDto
```
{
    "id": "",
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
    "next_combat_id": "",
    "session_id": "",
    "enemy_ids" : []
}
```

#### ChatDto
```
{
    "id": "",
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
!   "session_id": "",
    "log": [],
    "note": ""
}
```

#### CharacterDto
```
{
!   "id": "",
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
!*  "name": "",
!*  "user_id": "",
!   "class": CharacterClass,
!   "background: "",
!   "race": CharacterRace,
!   "alignment": CharacterAlignment,
*   "level" : 0,
*   "experience": 0,
*   "abilities: {
        "strength": 0,
        "dexterity": 0,
        "constitution": 0,
        "intelligence": 0,
        "wisdom": 0,
        "charisma": 0,
        "strength_modifier": 0,
        "dexterity_modifier": 0,
        "constitution_modifier": 0,
        "intelligence_modifier": 0,
        "wisdom_modifier": 0,
        "charisma_modifier": 0
    },
    "session_ids": [] 
}

CharacterAlignment = [
    "lawful good"
    "neutral good"
    "chaotic good"
    "lawful neutral"
    "neutral"
    "chaotic neutral"
    "lawful evil"
    "neutral evil"
    "chaotic evil"
]

CharacterClass = [
    "barbarian"
    "bard"
    "cleric"
    "druid"
    "fighter"
    "monk"
    "paladin"
    "ranger"
    "rogue"
    "sorcerer"
    "warlock"
    "wizard"
]

CharacterRace = [
    "dragonborn"
    "dwarf"
    "elf"
    "gnome"
    "half elf"
    "half orc"
    "halfling"
    "human"
    "tiefling"
]
```
