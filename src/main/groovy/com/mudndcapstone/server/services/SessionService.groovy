package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.request.SessionRequest
import org.springframework.web.server.ResponseStatusException

interface SessionService {
    List<Session> getAllSessions()
    Session getSessionById(Long id)
    Session createSession(SessionRequest sessionRequest) throws ResponseStatusException
    void deleteSession(Long id)
}