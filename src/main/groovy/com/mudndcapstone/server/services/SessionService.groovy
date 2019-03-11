package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Session

interface SessionService {
    List<Session> getAllSessions()
    Session getSessionById(Long id)
    Session createSession(Session session)
    void deleteSession(Long id)
}