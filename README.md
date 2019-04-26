#Dungeons And Disasters Server
##Spring Server for Senior Capstone Project 2019

A server for Dungeons and also a server for Dragons.

### Technologies
- [Java](https://www.java.com/en/)
- [Spring](https://spring.io)

### Routes

##### _Note: all routes prefixed with "/api/v1"_

#### Generic Routes
_X means the associated model_

_If there is a 404 for a generic route of a model, that means that route does not exist
or is overridden by a custom route_

| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |
| GET /X/{Xid} | Get X by id | | X |
| POST /X | Create X | X request | X |
| PUT /X/{Xid} | Update X by id | X | X |
| DELETE /X/{Xid} | Delete X by id | | HTTP 204 |

#### Custom Routes

##### Session
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |
| GET /sessions/{sessionId}/characters | Get all characters in a session | | [Character] |
| PUT /sessions/{sessionId}/characters/{characterId} | Connect Character to a session | | Session |
| GET /sessions/{sessionId}/chats?page&count | Get chat log of a session | | [String] |
| POST /sessions/{sessionId}/combats?insert | Create combat in a session | | Combat |
| PUT /sessions/{sessionId}/users/{userId} | Connect User to a session | | Session |
| GET /sessions/{sessionId}/maps | get maps of a session | | Map |

##### Chat
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |
| PUT /chats/{chatId} | Add message to chat log | Messenger | [String] |

##### Combat

##### Character


##### Being

##### User
| Route | Description | Body | Response |
| ------ | ------ | ------ | ------ |
| GET /users/{id} | Get a single user | | User |
| PUT /users/{id} | Update a single user | User | User |
| DELETE /users/{id} | Delete a single user | | |

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
