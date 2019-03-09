package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.DM
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class DMRepositoryTests {

    @Autowired
    private DMRepository dmRepository

    @Test
    void whenFindById_returnDM() {
        //Given
        DM dm = new DM()
        dmRepository.save(dm)
        //When
        Optional<DM> found = dmRepository.findById(dm.identifier)
        //Then
        Assert.assertEquals(found.get().id, dm.id)
    }
}
