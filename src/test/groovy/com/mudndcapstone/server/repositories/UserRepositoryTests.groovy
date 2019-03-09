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

    @Autowired UserRepository userRepository

    @Test
    void givenUser_whenUserSavedToRepository_thenUserReturned() {
        // Given
        User user = new User()
        User found

        // When
        userRepository.save(user)
        found = userRepository.findById(user.identifier).orElse(null)

        // Then
        Assert.assertNotNull(found)
        Assert.assertEquals(found.identifier, user.identifier)
    }

}
