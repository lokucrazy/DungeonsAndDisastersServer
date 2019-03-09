package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.repositories.SessionRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class SessionServiceImplTests {

    @Mock
    SessionRepository sessionRepository

    @InjectMocks
    SessionServiceImpl sessionService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void whenGetSessionById_returnSession() {
        //Given
        Session session = new Session()
        sessionRepository.save(session)
        //When
        Mockito.when(sessionRepository.findById(session.identifier).orElse(null)).thenReturn(Optional.of(session))
        //Then
        Assert.assertEquals(sessionService.getSessionById(session.identifier), session)
    }
}
