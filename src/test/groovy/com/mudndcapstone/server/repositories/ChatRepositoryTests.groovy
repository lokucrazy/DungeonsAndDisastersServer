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

    @Autowired
    private ChatRepository chatRepository

    @Test
    void whenFindById_returnChat() {
        //Given
        Chat chat = new Chat()
        chatRepository.save(chat)
        //When
        Optional<Chat> found = chatRepository.findById(chat.id)
        //Then
        Assert.assertEquals(found.get().id, chat.id)
    }
}
