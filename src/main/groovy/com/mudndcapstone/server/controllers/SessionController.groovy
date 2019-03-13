package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.models.dto.HistoryDto
import com.mudndcapstone.server.models.dto.SessionDto
import com.mudndcapstone.server.services.impl.CharacterServiceImpl
import com.mudndcapstone.server.services.impl.HistoryServiceImpl
import com.mudndcapstone.server.services.impl.SessionServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RestController
class SessionController {

    @Autowired SessionServiceImpl sessionService
    @Autowired CharacterServiceImpl characterService
    @Autowired HistoryServiceImpl historyService

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

    /* History */
    @GetMapping("/histories")
    ResponseEntity<Set<HistoryDto>> getAllHistories() {
        Set<History> histories = historyService.getAllHistories()
        Set<HistoryDto> historyDtos = historyService.buildDtoSetFrom(histories)
        new ResponseEntity<>(historyDtos, HttpStatus.OK)
    }

}
