package com.mudndcapstone.server.models.dto

import org.junit.Ignore
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
        ChatDto chatDto = new ChatDto()

        // Then
        assert !chatDto.identifier
        assert !chatDto.sessionId
        assert !chatDto.log
        assert !chatDto.note
    }

    @Test
    void givenChatDto_whenAddProperties_thenCharacterObjectHasProperties() {
        // Given
        ChatDto chatDto = new ChatDto()
        String testUuid = UUID.randomUUID().toString()
        List<String> log = [""]

        // When
        chatDto.setSessionId(testUuid)
        chatDto.setLog(log)
        chatDto.setNote("test note")

        // Then
        assert !chatDto.identifier
        assert chatDto.sessionId == testUuid
        assert chatDto.log == log
        assert chatDto.note == "test note"
    }

}
