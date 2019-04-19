package com.mudndcapstone.server.controllers

import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.Chat
import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.CharacterDto
import com.mudndcapstone.server.models.dto.CombatDto
import com.mudndcapstone.server.models.dto.HistoryDto
import com.mudndcapstone.server.models.dto.SessionDto
import com.mudndcapstone.server.services.CharacterService
import com.mudndcapstone.server.services.ChatService
import com.mudndcapstone.server.services.CombatService
import com.mudndcapstone.server.services.HistoryService
import com.mudndcapstone.server.services.MapService
import com.mudndcapstone.server.services.SessionService
import com.mudndcapstone.server.services.UserService
import com.mudndcapstone.server.services.UserService
import com.mudndcapstone.server.utils.PaginationHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

import javax.validation.Valid

@RestController
class SessionController {

    @Autowired SessionService sessionService
    @Autowired UserService userService
    @Autowired CharacterService characterService
    @Autowired CombatService combatService
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
        User dm = userService.getUserById(sessionDto.dmId)
        if (!dm) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user with given dmId not found")

        Session sessionRequest = sessionService.buildSessionFrom(sessionDto, dm)
        Session session

        if (!sessionRequest.identifier) {
            /* Entirely new session */
            sessionRequest.chatLog = chatService.createChat(new Chat(session: sessionRequest))
            sessionRequest.map = mapService.createMap(new Map(session: sessionRequest))
            session = sessionService.upsertSession(sessionRequest)
        } else {
            /* Previous session exists */
            session = sessionService.moveRelationships(sessionRequest.identifier)
            History history = historyService.convertSessionToHistory(sessionRequest.identifier)
            if (!session || !history) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "session not found to move relationships from")

            session.history = history
            session = sessionService.upsertSession(session)
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

    @PutMapping("/sessions/{sessionId}/users/{userId}")
    ResponseEntity<SessionDto> connectUserToSession(@PathVariable String sessionId, @PathVariable String userId) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "session could not be found")

        User user = userService.getUserById(userId)
        if (!user) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user could not be found")

        session = sessionService.attachUserToSession(session, user)
        if (!session) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "could not attach user to session")

        SessionDto sessionDto = sessionService.buildDtoFrom(session)
        new ResponseEntity<>(sessionDto, HttpStatus.OK)
    }

    @PutMapping("/sessions/{sessionId}/characters/{characterId}")
    ResponseEntity<SessionDto> connectCharacterToSession(@PathVariable String sessionId, @PathVariable String characterId) {
        if (!sessionId || !characterId) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sessionId or characterId could not be found")
        Session session = sessionService.getSessionById(sessionId)
        Character character = characterService.getCharacterById(characterId)
        if (!session || !character) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "session or character could not be found")

        session = sessionService.attachCharacterToSession(session, character)
        if (!session) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "character could not be added to session")

        SessionDto sessionDto = sessionService.buildDtoFrom(session)
        new ResponseEntity<>(sessionDto, HttpStatus.OK)
    }

    @Transactional(rollbackFor = ResponseStatusException)
    @PostMapping("/sessions/{sessionId}/combat")
    ResponseEntity<CombatDto> insertCombat(@PathVariable String sessionId, @RequestBody CombatDto combatDto) {
        Session session = sessionService.getSessionById(sessionId)
        if (!session) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "session could not be found")
        if (sessionId != combatDto.sessionId) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sessionId does not match combatDto sessionId")

        Combat combatRequest = combatService.buildCombatFrom(combatDto, session)
        Combat combat = combatService.insertCombatInPath(session, combatRequest)
        if (!combat) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "combat could not be created")

        CombatDto created = combatService.buildDtoFrom(combat)
        new ResponseEntity<>(created, HttpStatus.CREATED)
    }

    /* History */
    @GetMapping("/histories")
    ResponseEntity<Set<HistoryDto>> getAllHistories() {
        Set<History> histories = historyService.getAllHistories()
        Set<HistoryDto> historyDtos = historyService.buildDtoSetFrom(histories)
        new ResponseEntity<>(historyDtos, HttpStatus.OK)
    }

}
