package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Session
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class SessionRepositoryTests {

    @Autowired SessionRepository sessionRepository

    @Test
    void givenSession_whenSessionSavedToRepository_thenSessionReturned() {
        // Given
        Session session = new Session()
        Session found

        // When
        sessionRepository.save(session)
        found = sessionRepository.findById(session.identifier).orElse(null)

        // Then
        assert found
        assert session == found
    }

}
