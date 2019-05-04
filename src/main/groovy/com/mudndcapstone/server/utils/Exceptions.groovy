package com.mudndcapstone.server.utils

class Exceptions {

    /* General */
    final static String ROUTE_NOT_IMPLEMENTED = "Route currently not implemented"

    /* Misc. */
    final static String SESSION_CANT_CONNECT_CHARACTER_EXCEPTION = "Could not connect character to session"
    final static String SESSION_CANT_CONNECT_USER_EXCEPTION = "Could not connect user to session"
    final static String SESSION_COMBAT_NO_MATCH_EXCEPTION = "Cannot match session and combat's session ids"

    /* Enemy */
    final static String ENEMY_NOT_CREATED_EXCEPTION = "Enemy could not be created"
    final static String ENEMY_NOT_FOUND_EXCEPTION = "Enemy could not be found"

    /* NPC */
    final static String NPC_NOT_CREATED_EXCEPTION = "NPC could not be created"
    final static String NPC_NOT_FOUND_EXCEPTION = "NPC could not be found"

    /* Combat */
    final static String COMBAT_NOT_CREATED_EXCEPTION = "Combat could not be created"
    final static String COMBAT_NOT_FOUND_EXCEPTION = "Combat could not be found"

    /* Chat */
    final static String CHAT_NOT_CREATED_EXCEPTION = "Chat could not be created"
    final static String CHAT_NOT_FOUND_EXCEPTION = "Chat could not be found"
    final static String MESSAGE_NOT_ADDED_EXCEPTION = "Message could not be added to chat"

    /* Map */
    final static String MAP_NOT_CREATED_EXCEPTION = "Map could not be created"
    final static String MAP_NOT_FOUND_EXCEPTION = "Map could not be found"

    /* Character */
    final static String CHARACTER_NOT_CREATED_EXCEPTION = "Character could not be created"
    final static String CHARACTER_NOT_FOUND_EXCEPTION = "Character could not be found"

    /* User */
    final static String USER_NOT_CREATED_EXCEPTION = "User could not be created"
    final static String USER_NOT_FOUND_EXCEPTION = "User could not be found"
    final static String USERNAME_TAKEN_EXCEPTION = "Username already in use"

    /* History */
    final static String SESSION_CONVERSION_FAILED_EXCEPTION = "Could not convert session to history"

    /* Session */
    final static String SESSION_NOT_CREATED_EXCEPTION = "Session could not be created"
    final static String SESSION_NOT_FOUND_EXCEPTION = "Session could not be found"
    final static String SESSION_CANT_REFACTOR_RELATIONSHIP_EXCEPTION = "Could not refactor session's relationship"

}
