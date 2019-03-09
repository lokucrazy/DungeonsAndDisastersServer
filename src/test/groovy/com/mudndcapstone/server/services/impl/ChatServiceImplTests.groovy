package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.repositories.ChatRepository
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
class ChatServiceImplTests {

    @Mock
    private ChatRepository chatRepository

    @InjectMocks
    private ChatServiceImpl chatService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void whenGetChatById_returnChat() {
        //Given
        Chat chat = new Chat()
        chatRepository.save(chat)
        //When
        Mockito.when(chatRepository.findById(chat.identifier).orElse(null)).thenReturn(Optional.of(chat))
        //Then
        Assert.assertEquals(chatService.getChatById(chat.identifier), chat)
    }
}
