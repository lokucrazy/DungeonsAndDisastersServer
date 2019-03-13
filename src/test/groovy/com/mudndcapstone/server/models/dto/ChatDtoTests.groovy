package com.mudndcapstone.server.models.dto

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
        assert !chat.identifier
        assert !chat.sessionId
        assert !chat.log
        assert !chat.note
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
        assert !chat.identifier
        assert chat.sessionId == 500
        assert chat.log == log
        assert chat.note == "test note"
    }

}
