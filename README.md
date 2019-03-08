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
