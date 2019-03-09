package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.request.SessionRequest
import com.mudndcapstone.server.repositories.SessionRepository
import com.mudndcapstone.server.services.SessionService
import com.mudndcapstone.server.utils.ModelBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SessionServiceImpl implements SessionService {

    @Autowired private SessionRepository sessionRepository
    @Autowired private UserServiceImpl userService

    @Override
    List<Session> getAllSessions() {
        sessionRepository.findAll().asList()
    }

    @Override
    Session getSessionById(Long id) {
        sessionRepository.findById(id).orElse(null)
    }

    @Override
    Session createSession(SessionRequest sessionRequest) throws ResponseStatusException {
        // Find requested user, if user not found throw ResponseStatusException
        Long requestedUserIdentifier = sessionRequest.dmId
        User user = userService.getUserById(requestedUserIdentifier)
        if (!user) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find user with id ${requestedUserIdentifier}")

        Session session = ModelBuilder.buildSessionFrom(sessionRequest)
        session.setDm(user)
        sessionRepository.save(session)
    }

    @Override
    void deleteSession(Long id) {
        sessionRepository.deleteById(id)
    }

}
