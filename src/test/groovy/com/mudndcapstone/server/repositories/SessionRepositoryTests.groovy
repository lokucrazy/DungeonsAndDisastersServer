package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Session
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class SessionRepositoryTests {

    @Autowired
    private SessionRepository sessionRepository

    @Test
    void whenFindById_returnSession() {
        //Given
        Session session = new Session()
        sessionRepository.save(session)
        //When
        Optional<Session> found = sessionRepository.findById(session.identifier)
        //Then
        Assert.assertEquals(found.get().id, session.id)
    }
}
