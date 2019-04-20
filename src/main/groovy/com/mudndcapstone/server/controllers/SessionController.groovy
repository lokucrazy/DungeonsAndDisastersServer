package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.*
import com.mudndcapstone.server.models.dto.CombatDto
import com.mudndcapstone.server.models.dto.HistoryDto
import com.mudndcapstone.server.models.dto.SessionDto
import com.mudndcapstone.server.services.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

@RestController
class SessionController {

    @Autowired SessionService sessionService
    @Autowired UserService userService
    @Autowired CharacterService characterService
    @Autowired CombatService combatService
    @Autowired ChatService chatService
    @Autowired MapService mapService
    @Autowired HistoryService historyService

    /* Sessions */
    @GetMapping("/sessions")
    ResponseEntity<Set<SessionDto>> getAllSessions() {
        Set<Session> sessions = sessionService.getAllSessions()
        Set<SessionDto> sessionDtos = sessionService.buildDtoSetFrom(sessions)
        new ResponseEntity<>(sessionDtos, HttpStatus.OK)
    }

    @Transactional(rollbackFor = ResponseStatusException)
    @PostMapping("/sessions")
    ResponseEntity<SessionDto> createSession(@Valid @RequestBody SessionDto sessionDto) {
        User dm = userService.getUserById(sessionDto.dmId)
        if (!dm) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user with given dmId not found")

        Session sessionRequest = sessionService.buildSessionFrom(sessionDto, dm)
        Session session

        if (!sessionRequest.identifier) {
            /* Entirely new session */
            sessionRequest.chatLog = chatService.createChat(new Chat(session: sessionRequest))
            sessionRequest.map = mapService.createMap(new Map(session: sessionRequest))
            session = sessionService.upsertSession(sessionRequest)
        } else {
            /* Previous session exists */
            session = sessionService.moveRelationships(sessionRequest.identifier)
            History history = historyService.convertSessionToHistory(sessionRequest.identifier)
            if (!session || !history) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "session not found to move relationships from")

            session.history = history
            session = sessionService.upsertSession(session)
        }

        if (!session) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "session could not be created")

        SessionDto created = sessionService.buildDtoFrom(session)
        new ResponseEntity<>(created, HttpStatus.CREATED)
    }

    @GetMapping("/sessions/{sessionId}")
    ResponseEntity<SessionDto> getSessionById(@PathVariable String sessionId) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "session could not be found")

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

    /* History */
    @GetMapping("/histories")
    ResponseEntity<Set<HistoryDto>> getAllHistories() {
        Set<History> histories = historyService.getAllHistories()
        Set<HistoryDto> historyDtos = historyService.buildDtoSetFrom(histories)
        new ResponseEntity<>(historyDtos, HttpStatus.OK)
    }

}
