# DungeonsAndDragonsServer

A server for Dungeons and also a server for Dragons.

### Technologies
- [Java](https://www.java.com/en/)
- [Spring](https://spring.io)

### Routes

##### Note: all routes prefixed with "/api/v1"

#### User
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |
| GET /users | Get all users | | [User] |
| POST /users | Create new user | UserRequest | User |
| GET /users/{id} | Get a single user | | User |
| PUT /users/{id} | Update a single user | User | User |
| DELETE /users/{id} | Delete a single user | | |

### Objects
##### CharacterRequest
```
{
    "user_id": "",
    "name": "",
    "class": "",
    "background": "",
    "race": "",
    "alignment": ""
}
```

##### Character
```
{
    "identifier": "",
    "name": "",
    "level": 0,
    "experience": 0,
    "class": "",
    "background": "",
    "race": "",
    "alignment": "",
    "abilities": {},
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
    "user_id": "",
    "session_ids": []
}
```

##### ChatRequest
```
{
    "session_id": ""
}
```

##### Chat
```
{
    "identifier": "",
    "log": [],
    "note": "",
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
    "session_id": ""
}
```

##### CombatRequest
```
{
    "session_id": ""
}
```

##### Combat
```
{
    "identifier": "",
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
    "next_combat_id": "",
    "session_id": "",
    "enemy_ids": []
}
```

##### EnemyRequest
```
{
    "name": ""
}
```

##### Enemy
```
{
    "identifier": "",
    "health": 0,
    "is_alive": false,
    "initial_location": "",
    "abilities": {},
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
    "session_id": "",
    "dm_id": "",
    "combat_id": ""
}
```

##### HistoryRequest
```
{
}
```

##### History
```
{
    "identifier": "",
    "non_combat_log": [],
    "combat_log": [],
    "date_ended": "YYYY-mm-dd HH:mm a",
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
    "history_id": ""
}
```

##### MapRequest
```
{
    "session_id": ""
}
```

##### Map
```
{
    "identifier": "",
    "images": [],
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
    "session_id": ""
}
```

##### NPCRequest
```
{
    "name": ""
}
```

##### NPC
```
{
    "identifier": "",
    "name": "",
    "health": 0,
    "is_alive": false,
    "initial_location": "",
    "abilities": {},
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
    "session_id": "",
    "dm_id": ""
}
```

##### SessionRequest
```
{
    "dm_id": ""
}
```

##### Session
```
{
    "identifier": "",
    "non_combat_log": [],
    "combat_log": [],
    "date_ended": "YYYY-mm-dd HH:mm a",
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
    "dm_id": "",
    "history_id": "",
    "chat_id": "",
    "map_id": "",
    "combat_id": "",
    "npc_ids": [],
    "player_ids": [],
    "character_ids": []
}
```

##### UserRequest
```
{
    "username": "",
    "password": ""
}
```

##### User
```
{
    "identifier": "",
    "username": "",
    "password": "",
    "birthdate": "YYYY-mm-dd",
    "notes": [],
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
    "character_ids": [],
    "session_ids": [],
    "dm_session_ids": [],
    "npc_ids": []
}
```
