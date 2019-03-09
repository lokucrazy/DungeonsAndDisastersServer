package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.request.SessionRequest
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
    @Autowired HistoryService historyService

    /* Sessions */
    @GetMapping(value = "/sessions")
    ResponseEntity<List<Session>> getAllSessions() {
        List<Session> allSessions = sessionService.getAllSessions()
        new ResponseEntity<>(allSessions, HttpStatus.OK)
    }

    @PostMapping(value = "/sessions")
    ResponseEntity<Session> createSession(@Valid @RequestBody SessionRequest sessionRequest) {
        Session session = this.sessionService.createSession(sessionRequest)
        new ResponseEntity<>(session, HttpStatus.CREATED)
    }

    @GetMapping(value = "/sessions/{sessionId}")
    ResponseEntity<Session> getSessionById(@PathVariable Long sessionId) {
        Session session = this.sessionService.getSessionById(sessionId)
        new ResponseEntity<>(session, HttpStatus.OK)
    }

    @GetMapping(value = "/sessions/{sessionId}/characters")
    ResponseEntity<Set<Character>> getAllSessionsCharacters(@PathVariable Long sessionId) {
        Session session = this.sessionService.getSessionById(sessionId)
        if (!session) new ResponseEntity<>(HttpStatus.BAD_REQUEST)

        Set<Character> sessionCharacters = session.characters
        new ResponseEntity<>(sessionCharacters, HttpStatus.OK)
    }

    /* History */
    @GetMapping(value = "/histories")
    ResponseEntity<List<History>> getAllHistories() {
        List<History> allHistories = historyService.getAllHistories()
        new ResponseEntity<>(allHistories,HttpStatus.OK)
    }

}
