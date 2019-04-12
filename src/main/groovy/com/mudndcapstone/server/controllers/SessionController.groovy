package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.models.Map
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
import com.mudndcapstone.server.utils.PaginationHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

@RestController
class SessionController {

    @Autowired SessionService sessionService
    @Autowired UserService userService
    @Autowired CharacterService characterService
    @Autowired ChatService chatService
    @Autowired MapService mapService
    @Autowired HistoryService historyService

    /* Sessions */
    @GetMapping("/sessions")
    ResponseEntity<Set<SessionDto>> getAllSessions() {
        Set<Session> sessions = sessionService.getAllSessions()
        Set<SessionDto> sessionDtos = sessionService.buildDtoSetFrom(sessions)
        new ResponseEntity<>(sessionDtos, HttpStatus.OK)
    }

    @Transactional(rollbackFor = ResponseStatusException)
    @PostMapping("/sessions")
    ResponseEntity<SessionDto> createSession(@Valid @RequestBody SessionDto sessionDto) {
        if (!sessionDto || !sessionDto.dmId) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "session dto or session dm not found")
        Session sessionRequest = sessionService.buildSessionFrom(sessionDto)
        Session session
        if (!sessionRequest.identifier) {
            sessionService.createSession(sessionRequest)
            sessionRequest.chatLog = chatService.createChat(new Chat(session: sessionRequest))
            sessionRequest.map = mapService.createMap(new Map(session: sessionRequest))
            session = sessionService.updateSession(sessionRequest)
        } else {
            session = sessionService.moveRelationships(sessionRequest.identifier)
            History history = historyService.convertSessionToHistory(sessionRequest.identifier)
            if (session && history) {
                session.history = history
                session = sessionService.updateSession(session)
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "session not found to move relationships from")
            }
        }

        if (!session) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "session could not be created")

        SessionDto created = sessionService.buildDtoFrom(session)
        new ResponseEntity<>(created, HttpStatus.CREATED)
    }

    @GetMapping("/sessions/{sessionId}")
    ResponseEntity<SessionDto> getSessionById(@PathVariable String sessionId) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "session could not be found")

        SessionDto sessionDto = sessionService.buildDtoFrom(session)
        new ResponseEntity<>(sessionDto, HttpStatus.OK)
    }
  
    @PutMapping("/sessions/{sessionId}")
    ResponseEntity<SessionDto> updateSession(@PathVariable String sessionId, @Valid @RequestBody SessionDto sessionDto) {
        new ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @PutMapping("/sessions/{sessionId}/users/{userId}")
    ResponseEntity<SessionDto> connectUserToSession(@PathVariable String sessionId, @PathVariable String userId) {
        if (!sessionId || !userId ) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sessionId or userIds not found")
        Session session = sessionService.getSessionById(sessionId)
        User user = userService.getUserById(userId)
        SessionDto sessionDto

        session = sessionService.attachUserToSession(session, user)
        if (session) {
            sessionDto = sessionService.buildDtoFrom(session)
            return new ResponseEntity<SessionDto>(sessionDto, HttpStatus.OK)
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "could not attach user to session")
        }
    }

    @DeleteMapping("/sessions/{sessionId}")
    ResponseEntity deleteSession(@PathVariable String sessionId) {
        sessionService.deleteSession(sessionId)
        new ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/sessions/{sessionId}/characters")
    ResponseEntity<Set<CharacterDto>> getAllSessionsCharacters(@PathVariable String sessionId) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) return new ResponseEntity<>(HttpStatus.BAD_REQUEST)
        if (!session.characters) return new ResponseEntity<>([], HttpStatus.OK)

        Set<CharacterDto> characterDtos = characterService.buildDtoSetFrom(session.characters)
        new ResponseEntity<>(characterDtos, HttpStatus.OK)
    }

    @GetMapping("/sessions/{sessionId}/chats")
    ResponseEntity<List<String>> getSessionChats(@PathVariable String sessionId, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> count) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "session not found")
        if (!session.chatLog) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "session does not a have chat node attached")
        if (!session.chatLog.log) return new ResponseEntity<>([], HttpStatus.OK)

        List<String> chats = PaginationHandler.getPage(session.chatLog.log, page, count)
        new ResponseEntity<>(chats, HttpStatus.OK)
    }

    @PostMapping("/sessions/{sessionId}/chats")
    ResponseEntity<List<String>> createChat(@PathVariable String sessionId, @RequestBody String message, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> count) {
        // TODO: validate and strip string
        Session session = sessionService.getSessionById(sessionId)
        if (!session) return new ResponseEntity<>(HttpStatus.BAD_REQUEST)

        // Find or create DTO with session id and message appended to log
        ChatDto chatDto = session.chatLog ? chatService.buildDtoFrom(session.chatLog) : new ChatDto()
        if (!chatDto.sessionId) chatDto.setSessionId(session.identifier)
        chatDto.addMessage(message)

        // Update chat node with new log
        Chat updated = chatService.createChatFromDTO(chatDto)

        List<String> chats = PaginationHandler.getPage(updated.log, page, count)
        new ResponseEntity<>(chats, HttpStatus.OK)
    }

    /* History */
    @GetMapping("/histories")
    ResponseEntity<Set<HistoryDto>> getAllHistories() {
        Set<History> histories = historyService.getAllHistories()
        Set<HistoryDto> historyDtos = historyService.buildDtoSetFrom(histories)
        new ResponseEntity<>(historyDtos, HttpStatus.OK)
    }

}
