package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.DM
import com.mudndcapstone.server.repositories.DMRepository
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
class DMServiceImplTests {

    @Mock
    DMRepository dmRepository

    @InjectMocks
    DMServiceImpl dmService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void whenGetDmById_returnDm() {
        //Given
        DM dm = new DM()
        dmRepository.save(dm)
        //When
        Mockito.when(dmRepository.findDMById(dm.identifier).orElse(null)).thenReturn(Optional.of(dm))
        //Then
        Assert.assertEquals(dmService.getDMById(dm.identifier), dm)
    }
}
