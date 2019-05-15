package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.models.Messenger
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.SessionDto
import com.mudndcapstone.server.services.*
import com.mudndcapstone.server.utils.Exceptions
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.server.ResponseStatusException

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

@RunWith(SpringRunner)
@SpringBootTest
class SessionControllerTests {

    @Mock SessionService sessionService
    @Mock UserService userService
    @Mock CharacterService characterService
    @Mock CombatService combatService
    @Mock ChatService chatService
    @Mock MapService mapService
    @Mock HistoryService historyService

    @InjectMocks SessionController sessionController

    @Rule
    public ExpectedException thrown = ExpectedException.none()

    @Before
    void setup() {
        MockitoAnnotations.initMocks(this)
    }

    /* createSession tests */
    @Test()
    void givenNullSessionDto_whenAttemptCreateSession_thenThrowException(){
        // Given
        SessionDto sessionDto = null

        thrown.expect(NullPointerException)

        // When
        sessionController.createSession(sessionDto)

        // Then
        // exception thrown
    }

    @Test()
    void givenEmptySessionDto_whenAttemptCreateSession_thenThrowException() {
        // Given
        SessionDto sessionDto = new SessionDto()

        thrown.expect(ResponseStatusException)
        thrown.expectMessage(Exceptions.USER_NOT_FOUND_EXCEPTION)

        // When
        sessionController.createSession(sessionDto)

        // Then
        // exception thrown
    }

    @Test
    void givenValidSessionDtoWithInvalidDMId_whenAttemptCreateSession_thenThrowException() {
        // Given
        SessionDto sessionDto = new SessionDto(dmId: "fake_dm_id")

        thrown.expect(ResponseStatusException)
        thrown.expectMessage(Exceptions.USER_NOT_FOUND_EXCEPTION)

        // When
        sessionController.createSession(sessionDto)

        // Then
        // exception thrown
    }

    @Test
    void givenValidSessionDtoWithNoPreviousIdentifier_whenAttemptCreateSession_thenCreateSession() {
        // Given
        User user = new User(identifier: "user_id")
        SessionDto sessionDto = new SessionDto(dmId: user.identifier)
        Session session = new Session(dm: user)
        ResponseEntity<SessionDto> response

        // When
        when(userService.getUserById("user_id")).thenReturn(user)
        when(sessionService.buildSessionFrom(sessionDto, user)).thenReturn(session)
        when(sessionService.upsertSession(session)).thenReturn(session)
        response = sessionController.createSession(sessionDto)

        // Then
        verify(userService).getUserById("user_id")
        verify(sessionService).buildSessionFrom(sessionDto, user)
        verify(sessionService).upsertSession(session)
        assert response.statusCode == HttpStatus.CREATED
    }

    @Test
    void givenValidSessionDtoWithPreviousIdentifier_whenAttemptCreateSession_thenRefactorOldSessionAndCreateNewSession() {
        // Given
        User user = new User(identifier: "user_id")
        SessionDto sessionDto = new SessionDto(identifier: "previous_session_id", dmId: user.identifier)
        Session old = new Session(identifier: "previous_session_id", dm: user)
        History history = new History(identifier: "previous_session_id")
        Session session = new Session(identifier: "new_session_id", dm: user, history: history)
        ResponseEntity<SessionDto> response

        // When
        when(userService.getUserById("user_id")).thenReturn(user)
        when(sessionService.buildSessionFrom(sessionDto, user)).thenReturn(old)
        when(sessionService.moveRelationships("previous_session_id")).thenReturn(old)
        when(historyService.convertSessionToHistory("previous_session_id")).thenReturn(history)
        when(sessionService.upsertSession(any(Session))).thenReturn(session)
        response = sessionController.createSession(sessionDto)

        // Then
        verify(userService).getUserById("user_id")
        verify(sessionService).buildSessionFrom(sessionDto, user)
        verify(sessionService).moveRelationships("previous_session_id")
        verify(historyService).convertSessionToHistory("previous_session_id")
        verify(sessionService).upsertSession(any(Session))
        assert response.statusCode == HttpStatus.CREATED
    }

    /* getSessionById tests */
    @Test
    void givenInvalidSessionId_whenGetSessionByIdCalled_thenExceptionThrown() {
        // Given
        String sessionId = "fake_session_id"

        thrown.expect(ResponseStatusException)
        thrown.expectMessage(Exceptions.SESSION_NOT_FOUND_EXCEPTION)

        // When
        sessionController.getSessionById(sessionId)

        // Then
        // exception thrown
    }

    @Test
    void givenValidSessionId_whenGetSessionByIdCalled_thenReturnSession() {
        // Given
        Session session = new Session(identifier: "fake_session_id")
        ResponseEntity<SessionDto> response

        // When
        when(sessionService.getSessionById("fake_session_id")).thenReturn(session)
        response = sessionController.getSessionById("fake_session_id")

        // Then
        verify(sessionService).getSessionById("fake_session_id")
        assert response.statusCode == HttpStatus.OK
    }

    /* updateSession tests */

    /* setSessionState tests */

    /* deleteSession tests */
    @Test
    void givenSessionId_whenDeleteSession_thenResponseReturned() {
        // Given
        String sessionId = "fake_session_id"
        ResponseEntity response

        // When
        response = sessionController.deleteSession(sessionId)

        // Then
        verify(sessionService).deleteSession(sessionId)
        assert response.statusCode == HttpStatus.NO_CONTENT
    }

    /* addCombatMessage tests */
    @Test
    void givenInvalidSessionId_whenAddCombatMessageCalled_thenExceptionThrown() {
        // Given
        String sessionId = "fake_session_id"

        thrown.expect(ResponseStatusException)
        thrown.expectMessage(Exceptions.SESSION_NOT_FOUND_EXCEPTION)

        // When
        sessionController.addCombatMessage(sessionId, null, false)

        // Then
        // exception thrown
    }

    @Test
    void givenFalseCombatParam_whenAddCombatMessageCalled_thenMessageAddedToNonCombatLog() {
        // Given
        String sessionId = "session_id"
        List<String> nonCombatLog = ["non combat log"]
        Session session = new Session(identifier: sessionId, nonCombatLog: nonCombatLog)
        Messenger messenger = new Messenger(body: "test_body")
        ResponseEntity<List<String>> response


        // When
        when(sessionService.getSessionById(sessionId)).thenReturn(session)
        when(sessionService.addMessage(session, messenger.body, false)).thenReturn(session)
        response = sessionController.addCombatMessage(sessionId, messenger, false)

        // Then
        verify(sessionService).getSessionById(sessionId)
        verify(sessionService).addMessage(session, messenger.body, false)
        assert response.statusCode == HttpStatus.OK
    }

    @Test
    void givenTrueCombatParam_whenAddCombatMessageCalled_thenMessageAddedToCombatLog() {
        // Given
        String sessionId = "session_id"
        List<String> combatLog = ["combat log"]
        Session session = new Session(identifier: sessionId, combatLog: combatLog)
        Messenger messenger = new Messenger(body: "test_body")
        ResponseEntity<List<String>> response


        // When
        when(sessionService.getSessionById(sessionId)).thenReturn(session)
        when(sessionService.addMessage(session, messenger.body, true)).thenReturn(session)
        response = sessionController.addCombatMessage(sessionId, messenger, true)

        // Then
        verify(sessionService).getSessionById(sessionId)
        verify(sessionService).addMessage(session, messenger.body, true)
        assert response.statusCode == HttpStatus.OK
    }
}
