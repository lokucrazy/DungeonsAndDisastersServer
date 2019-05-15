package com.mudndcapstone.server.models

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class ChatTests {

    @Test
    void givenEmptyChat_thenReturnEmptyChatObject() {
        // Given
        Chat chat = new Chat()

        // Then
        assert !chat.identifier
        assert !chat.log
        assert !chat.session
    }

    @Test
    void givenChat_whenAddProperties_thenCharacterObjectHasProperties() {
        // Given
        Chat chat = new Chat()
        List<String> log = [""]
        Session session = new Session()

        // When
        chat.setLog(log)
        chat.setNote("test note")
        chat.setSession(session)

        // Then
        assert !chat.identifier
        assert chat.log == log
        assert chat.note == "test note"
        assert chat.session == session
    }

}
