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
    ResponseEntity<List<SessionDto>> getAllSessions() {
        List<Session> sessions = sessionService.getAllSessions()
        List<SessionDto> sessionDtos = sessionService.buildDtoListFrom(sessions)
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
    ResponseEntity<SessionDto> getSessionById(@PathVariable Long sessionId) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) return new ResponseEntity<>(HttpStatus.BAD_REQUEST)

        SessionDto sessionDto = sessionService.buildDtoFrom(session)
        new ResponseEntity<>(sessionDto, HttpStatus.OK)
    }
  
    @PutMapping("/sessions/{sessionId}")
    ResponseEntity<SessionDto> updateSession(@PathVariable Long sessionId, @Valid @RequestBody SessionDto sessionDto) {
        new ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @DeleteMapping("/sessions/{sessionId}")
    ResponseEntity deleteSession(@PathVariable Long sessionId) {
        sessionService.deleteSession(sessionId)
        new ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/sessions/{sessionId}/characters")
    ResponseEntity<List<CharacterDto>> getAllSessionsCharacters(@PathVariable Long sessionId) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) return new ResponseEntity<>(HttpStatus.BAD_REQUEST)
        if (!session.characters) return new ResponseEntity<>([], HttpStatus.OK)

        List<CharacterDto> characterDtos = characterService.buildDtoListFrom(session.characters)
        new ResponseEntity<>(characterDtos, HttpStatus.OK)
    }

    /* History */
    @GetMapping("/histories")
    ResponseEntity<List<HistoryDto>> getAllHistories() {
        List<History> histories = historyService.getAllHistories()
        List<HistoryDto> historyDtos = historyService.buildDtoListFrom(histories)
        new ResponseEntity<>(historyDtos, HttpStatus.OK)
    }

}
