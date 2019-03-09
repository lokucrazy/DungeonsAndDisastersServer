package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.services.HistoryService
import com.mudndcapstone.server.services.SessionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/sessions")
class SessionController {

    @Autowired SessionService sessionService

    @Autowired HistoryService historyService

    @GetMapping
    ResponseEntity<List<Session>> getAllSessions() {
        List<Session> allSessions = sessionService.getAllSessions()
        new ResponseEntity<List<Session>>(allSessions, HttpStatus.OK)
    }

    @GetMapping(value = "/histories")
    ResponseEntity<List<History>> getAllHistories() {
        List<History> allHistories = historyService.getAllHistories()
        new ResponseEntity<List<History>>(allHistories,HttpStatus.OK)
    }
}
