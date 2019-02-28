package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.repositories.SessionRepository
import com.mudndcapstone.server.services.SessionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SessionServiceImpl implements SessionService {

    @Autowired SessionRepository sessionRepository

    @Override
    List<Session> getAllSessions() {
        sessionRepository.findAll().asList()
    }

    @Override
    Session getSessionById(Long id) {
        sessionRepository.findById(id).orElse(null)
    }

    @Override
    Session createSession(Session session) {
        sessionRepository.save(session)
    }

    @Override
    void deleteSession(Long id) {
        sessionRepository.deleteById(id)
    }
}
