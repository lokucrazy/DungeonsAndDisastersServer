package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.models.dto.ChatDto
import com.mudndcapstone.server.models.dto.HistoryDto
import com.mudndcapstone.server.models.dto.SessionDto
import com.mudndcapstone.server.services.CharacterService
import com.mudndcapstone.server.services.ChatService
import com.mudndcapstone.server.services.HistoryService
import com.mudndcapstone.server.services.SessionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RestController
class SessionController {

    @Autowired SessionService sessionService
    @Autowired CharacterService characterService
    @Autowired ChatService chatService
    @Autowired HistoryService historyService

    /* Sessions */
    @GetMapping("/sessions")
    ResponseEntity<Set<SessionDto>> getAllSessions() {
        Set<Session> sessions = sessionService.getAllSessions()
        Set<SessionDto> sessionDtos = sessionService.buildDtoSetFrom(sessions)
        new ResponseEntity<>(sessionDtos, HttpStatus.OK)
    }

    @PostMapping("/sessions")
    ResponseEntity<SessionDto> createSession(@Valid @RequestBody SessionDto sessionDto) {
        Session sessionRequest = sessionService.buildSessionFrom(sessionDto)
        Session session = sessionService.createSession(sessionRequest)
        if (!session) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)

        SessionDto created = sessionService.buildDtoFrom(session)
        new ResponseEntity<>(created, HttpStatus.CREATED)
    }

    @GetMapping("/sessions/{sessionId}")
    ResponseEntity<SessionDto> getSessionById(@PathVariable String sessionId) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) return new ResponseEntity<>(HttpStatus.BAD_REQUEST)

        SessionDto sessionDto = sessionService.buildDtoFrom(session)
        new ResponseEntity<>(sessionDto, HttpStatus.OK)
    }
  
    @PutMapping("/sessions/{sessionId}")
    ResponseEntity<SessionDto> updateSession(@PathVariable String sessionId, @Valid @RequestBody SessionDto sessionDto) {
        new ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @DeleteMapping("/sessions/{sessionId}")
    ResponseEntity deleteSession(@PathVariable String sessionId) {
        sessionService.deleteSession(sessionId)
        new ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/sessions/{sessionId}/characters")
    ResponseEntity<Set<CharacterDto>> getAllSessionsCharacters(@PathVariable String sessionId) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) return new ResponseEntity<>(HttpStatus.BAD_REQUEST)
        if (!session.characters) return new ResponseEntity<>([], HttpStatus.OK)

        Set<CharacterDto> characterDtos = characterService.buildDtoSetFrom(session.characters)
        new ResponseEntity<>(characterDtos, HttpStatus.OK)
    }

    @GetMapping("/sessions/{sessionId}/chats")
    ResponseEntity<ChatDto> getSessionChats(@PathVariable String sessionId) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) return new ResponseEntity<>(HttpStatus.BAD_REQUEST)
        if (!session.chatLog) return new ResponseEntity<>([], HttpStatus.OK)

        ChatDto chatDto = chatService.buildDtoFrom(session.chatLog)
        new ResponseEntity<>(chatDto, HttpStatus.OK) // TODO: Implement pagination
    }

    @PostMapping("/sessions/{sessionId}/chats")
    ResponseEntity<ChatDto> createChat(@PathVariable String sessionId, @RequestBody String message) {
        // TODO: validate and strip string
        Session session = sessionService.getSessionById(sessionId)
        if (!session) return new ResponseEntity<>(HttpStatus.BAD_REQUEST)

        // Find or create DTO with session id and message appended to log
        ChatDto chatDto = session.chatLog ? chatService.buildDtoFrom(session.chatLog) : new ChatDto()
        if (!chatDto.sessionId) chatDto.setSessionId(session.identifier)
        chatDto.addMessage(message)

        // Update chat node with new log
        Chat updated = chatService.createChat(chatDto)
        ChatDto updatedDto = chatService.buildDtoFrom(updated)

        new ResponseEntity<>(updatedDto, HttpStatus.OK) // TODO: Implement pagination
    }

    /* History */
    @GetMapping("/histories")
    ResponseEntity<Set<HistoryDto>> getAllHistories() {
        Set<History> histories = historyService.getAllHistories()
        Set<HistoryDto> historyDtos = historyService.buildDtoSetFrom(histories)
        new ResponseEntity<>(historyDtos, HttpStatus.OK)
    }

}
