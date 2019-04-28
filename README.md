#Dungeons And Disasters Server
##Spring Server for Senior Capstone Project 2019

A server for Dungeons and also a server for Dragons.

### Technologies
- [Java](https://www.java.com/en/)
- [Spring](https://spring.io)

### Routes

##### Note: all routes prefixed with "/api/v1"

##### Generic Routes
_generic route can either not exist or be overridden by a custom route_

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
| POST /sessions/{sessionId}/combats | Create combat in a Session | CombatDto | CombatDto |
| PUT /sessions/{sessionId}/characters/{characterId} | Connect Character to a Session | | SessionDto |
| PUT /sessions/{sessionId}/users/{userId} | Connect User to a Session | | SessionDto |

#### User (users, dms)
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |
| GET /users/{userId}/characters | Get all Characters in a User | | [CharacterDto] |
| POST /dms/{dmId}/npcs | Create an NPC | NPCDto | NPCDto |
| POST /users/{userId}/characters | Create a Character | CharacterDto | CharacterDto |

#### Being (npcs, enemies)
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |

#### Character
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |

#### Chat
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |

#### Combat
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |
| POST /combats/{combatId}/enemies | Create Enemy in a Combat | EnemyDto | EnemyDto |
#### Map
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |

### Objects

##### UserRequest (Only for creating new users via POST /users)
```
{
    "username": "",
    "password": "",
    "birthdate": "YYYY-mm-dd"
}
```

##### User
```
{
    "id": "",
    "username": "",
    "password": "",
    "birthdate": "YYYY-mm-dd",
    "notes": [],
    "created_at": "YYYY-mm-dd HH:mm a",
    "modified_at": "YYYY-mm-dd HH:mm a",
    "characters": [],
    "sessions": []
}
```

