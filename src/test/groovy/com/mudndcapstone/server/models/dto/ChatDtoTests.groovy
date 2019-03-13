package com.mudndcapstone.server.models.dto

import com.mudndcapstone.server.models.Chat
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

    @Test
    void givenChatWithEmptyLog_whenAddMessage_thenChatHasLogWithOneMessage() {
        // Given
        ChatDto chatDto = new ChatDto()
        String message = "test message"

        // When
        chatDto.addMessage(message)

        // Then
        assert chatDto.log
        assert chatDto.log.size() == 1
        assert chatDto.log[0] == message
    }

    @Test
    void givenChatWithLog_whenAddMessage_thenChatHasMessageAddedToLog() {
        // Given
        ChatDto chatDto = new ChatDto()
        chatDto.log = ["hey player2", "sup player1?"]
        String message = "player3 also says hi"

        // When
        chatDto.addMessage(message)

        // Then
        assert chatDto.log
        assert chatDto.log.size() == 3
        assert chatDto.log[-1] == message
    }

}
