package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.repositories.ChatRepository
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
class ChatServiceTests {

    @Mock ChatRepository chatRepository

    @InjectMocks
    ChatService chatService

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenChat_whenChatRepositorySavesChat_thenChatServiceReturnsChat() {
        // Given
        Chat chat = new Chat()
        Chat found

        // When
        chatRepository.save(chat)
        Mockito.when(chatRepository.findById(chat.identifier)).thenReturn(Optional.of(chat))
        found = chatService.getChatById(chat.identifier)

        // Then
        assert chat == found
    }

}
