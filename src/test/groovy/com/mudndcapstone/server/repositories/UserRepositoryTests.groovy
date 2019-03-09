package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.User
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository

    @Test
    void whenFindById_returnUser() {
        //Given
        User user = new User()
        userRepository.save(user)
        //When
        Optional<User> found = userRepository.findById(user.identifier)
        //Then
        Assert.assertEquals(found.get().id, user.id)
    }
}
