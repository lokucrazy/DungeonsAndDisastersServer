package com.mudndcapstone.server.models.dto

import com.mudndcapstone.server.models.Session
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class ChatDtoTests {

    @Test
    void givenEmptyChatDto_thenReturnEmptyChatDtoObject() {
        // Given
        ChatDto chat = new ChatDto()

        // Then
        Assert.assertNull(chat.identifier)
        Assert.assertNull(chat.sessionId)
        Assert.assertNull(chat.log)
        Assert.assertNull(chat.note)
    }

    @Test
    void givenChatDto_whenAddProperties_thenCharacterObjectHasProperties() {
        // Given
        ChatDto chat = new ChatDto()
        List<String> log = [""]

        // When
        chat.setSessionId(500)
        chat.setLog(log)
        chat.setNote("test note")

        // Then
        Assert.assertNull(chat.identifier)
        Assert.assertEquals(chat.sessionId, 500)
        Assert.assertEquals(chat.log, log)
        Assert.assertEquals(chat.note, "test note")
    }

}
