package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.*
import com.mudndcapstone.server.models.dto.HistoryDto
import com.mudndcapstone.server.models.dto.SessionDto
import com.mudndcapstone.server.services.*
import com.mudndcapstone.server.utils.Exceptions
import com.mudndcapstone.server.utils.PaginationHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

@RestController
@CrossOrigin("*")
class SessionController {

    @Autowired SessionService sessionService
    @Autowired UserService userService
    @Autowired CharacterService characterService
    @Autowired CombatService combatService
    @Autowired ChatService chatService
    @Autowired MapService mapService
    @Autowired HistoryService historyService

    /* Sessions */
    @Transactional(rollbackFor = ResponseStatusException)
    @PostMapping("/sessions")
    ResponseEntity<SessionDto> createSession(@Valid @RequestBody SessionDto sessionDto) {
        User dm = userService.getUserById(sessionDto.dmId)
        if (!dm) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.USER_NOT_FOUND_EXCEPTION)

        Session sessionRequest = sessionService.buildSessionFrom(sessionDto, dm)
        Session session

        if (!sessionRequest.identifier) {
            /* Entirely new session */
            if (sessionRequest.identifier == "") { sessionRequest.identifier = null }
            sessionRequest.chatLog = chatService.upsertChat(new Chat(session: sessionRequest))
            sessionRequest.map = mapService.upsertMap(new Map(session: sessionRequest))
            session = sessionService.upsertSession(sessionRequest)
        } else {
            /* Previous session exists */
            session = sessionService.moveRelationships(sessionRequest.identifier)
            if (!session) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.SESSION_CANT_REFACTOR_RELATIONSHIP_EXCEPTION)

            History history = historyService.convertSessionToHistory(sessionRequest.identifier)
            if (!history) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.SESSION_CONVERSION_FAILED_EXCEPTION)

            session.history = history
            session = sessionService.upsertSession(session)
        }

        if (!session) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.SESSION_NOT_CREATED_EXCEPTION)

        SessionDto created = sessionService.buildDtoFrom(session)
        new ResponseEntity<>(created, HttpStatus.CREATED)
    }

    @GetMapping("/sessions/{sessionId}")
    ResponseEntity<SessionDto> getSessionById(@PathVariable String sessionId) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.SESSION_NOT_FOUND_EXCEPTION)

        SessionDto sessionDto = sessionService.buildDtoFrom(session)
        new ResponseEntity<>(sessionDto, HttpStatus.OK)
    }
  
    @PutMapping("/sessions/{sessionId}")
    ResponseEntity<SessionDto> updateSession(@PathVariable String sessionId, @Valid @RequestBody SessionDto sessionDto) {
        if (!sessionService.existsById(sessionId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, Exceptions.SESSION_NOT_FOUND_EXCEPTION)
        User dm = userService.getDMById(sessionDto.dmId)
        if (!dm) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.USER_NOT_FOUND_EXCEPTION)
        Session sessionRequest = sessionService.buildSessionFrom(sessionDto, dm)
        sessionRequest.identifier = sessionId
        Session session

        session = sessionService.upsertSession(sessionRequest)
        if (!session) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.SESSION_NOT_UPDATED_EXCEPTION)

        SessionDto updated = sessionService.buildDtoFrom(session)
        new ResponseEntity<>(updated, HttpStatus.OK)
    }

    @PatchMapping("/sessions/{sessionId}/state")
    ResponseEntity<SessionDto> setSessionState(@PathVariable String sessionId, @Valid @RequestBody SessionState state) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.SESSION_NOT_FOUND_EXCEPTION)

        Session updated = sessionService.setSessionState(session, state)
        SessionDto sessionDto = sessionService.buildDtoFrom(updated)
        new ResponseEntity<>(sessionDto, HttpStatus.OK)
    }

    @DeleteMapping("/sessions/{sessionId}")
    ResponseEntity deleteSession(@PathVariable String sessionId) {
        sessionService.deleteSession(sessionId)
        new ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PutMapping("/sessions/{sessionId}/log")
    ResponseEntity<List<String>> addCombatMessage(@PathVariable String sessionId, @Valid @RequestBody Messenger messenger, @RequestParam boolean combat) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Exceptions.SESSION_NOT_FOUND_EXCEPTION)

        session = sessionService.addMessage(session, messenger.body, combat)
        if (!session) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Exceptions.MESSAGE_NOT_ADDED_EXCEPTION)

        List<String> messages = PaginationHandler.getPage(combat ? session.combatLog : session.nonCombatLog, null, null)
        new ResponseEntity<>(messages, HttpStatus.OK)
    }

}
