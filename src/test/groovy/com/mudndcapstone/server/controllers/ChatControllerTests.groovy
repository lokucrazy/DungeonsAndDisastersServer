package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.dto.ChatDto
import com.mudndcapstone.server.services.impl.ChatServiceImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class ChatControllerTests {

    @Mock ChatServiceImpl chatService

    @InjectMocks
    ChatController chatController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void givenChatList_whenChatServiceReturnsList_thenChatControllerReturnsList() {
        // Given
        Set<Chat> chats = [new Chat()]
        Set<ChatDto> chatDtos = chatService.buildDtoSetFrom(chats)

        // When
        Mockito.when(chatService.getAllChats()).thenReturn(chats)

        // Then
        ResponseEntity response = chatController.getAllChats()
        Assert.assertEquals(response.statusCode, HttpStatus.OK)
        Assert.assertEquals(response.body, chatDtos)
        Mockito.verify(chatService, Mockito.atLeastOnce()).getAllChats()
    }

}
