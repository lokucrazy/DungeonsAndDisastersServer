package com.mudndcapstone.server.models

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class ChatTests {

    @Test
    void givenEmptyChat_thenReturnAlmostEmptyChatObject() {
        // Given
        Chat chat = new Chat()

        // Then
        Assert.assertNull(chat.id)
        Assert.assertNull(chat.log)
        Assert.assertNull(chat.session)
    }

    @Test
    void givenChat_whenAddProperties_thenCharacterObjectHasProperties() {
        // Given
        Chat chat = new Chat()
        List<String> log = new ArrayList<>()
        Session session = new Session()

        // When
        chat.setLog(log)
        chat.setNote("test note")
        chat.setSession(session)

        // Then
        Assert.assertNull(chat.id)
        Assert.assertEquals(chat.log, log)
        Assert.assertEquals(chat.note, "test note")
        Assert.assertEquals(chat.session, session)
    }

}
