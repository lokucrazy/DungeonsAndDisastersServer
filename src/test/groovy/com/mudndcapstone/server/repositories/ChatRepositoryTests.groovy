package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.Chat
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataNeo4jTest
class ChatRepositoryTests {

    @Autowired ChatRepository chatRepository

    @Test
    void givenChat_whenChatSavedToRepository_thenChatReturned() {
        // Given
        Chat chat = new Chat()
        Chat found

        // When
        chatRepository.save(chat)
        found = chatRepository.findById(chat.identifier).orElse(null)

        // Then
        Assert.assertNotNull(found)
        Assert.assertEquals(found.identifier, chat.identifier)
    }

}
