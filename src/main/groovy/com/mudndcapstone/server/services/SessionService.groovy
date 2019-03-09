package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.request.SessionRequest

interface SessionService {
    List<Session> getAllSessions()
    Session getSessionById(Long id)
    Session createSession(SessionRequest sessionRequest)
    void deleteSession(Long id)
}