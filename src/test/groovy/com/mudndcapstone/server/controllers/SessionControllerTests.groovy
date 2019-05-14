package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.models.dto.ChatDto
import com.mudndcapstone.server.models.dto.HistoryDto
import com.mudndcapstone.server.models.dto.SessionDto
import com.mudndcapstone.server.services.CharacterService
import com.mudndcapstone.server.services.ChatService
import com.mudndcapstone.server.services.HistoryService
import com.mudndcapstone.server.services.MapService
import com.mudndcapstone.server.services.SessionService
import com.mudndcapstone.server.services.UserService
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import static org.mockito.ArgumentMatchers.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class SessionControllerTests {

    @Mock SessionService sessionService
    @Mock CharacterService characterService
    @Mock ChatService chatService
    @Mock MapService mapService
    @Mock HistoryService historyService
    @Mock UserService userService

    @InjectMocks
    SessionController sessionController

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    void getResponseEntity_whenGivenSessionId_forGetSessionById() {
        // Mocks

        // Test

        // Assert
    }

    @Test
    void getResponseStatusException_whenGivenBadSessionId_forGetSessionById() {
        // Mocks

        // Test

        // Assert
    }

}
