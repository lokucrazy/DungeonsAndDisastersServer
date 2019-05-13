package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.SessionState
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.Character
import com.mudndcapstone.server.models.dto.SessionDto
import com.mudndcapstone.server.repositories.CharacterRepository
import com.mudndcapstone.server.repositories.SessionRepository
import com.mudndcapstone.server.utils.Auditor
import com.mudndcapstone.server.repositories.UserRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class SessionService {

    @Autowired SessionRepository sessionRepository
    @Autowired UserRepository userRepository
    @Autowired CharacterRepository characterRepository
    @Autowired UserService userService
    @Autowired ModelMapper modelMapper

    Session setSessionState(Session session, SessionState state) {
        session.running = state.running

        Auditor.enableAuditing(session)
        sessionRepository.save(session)
    }

    Set<Session> getAllSessions() {
        sessionRepository.findAll().toSet()
    }

    boolean existsBySessionId(String id) {
        sessionRepository.existsById(id)
    }

    Session getSessionById(String id) {
        sessionRepository.findById(id).orElse(null)
    }

    Session upsertSession(Session session) {
        if (!session.dm) return null

        Auditor.enableAuditing(session)
        sessionRepository.save(session)
    }

    Session buildAndCreateSession(SessionDto sessionDto, User dm) {
        Session sessionRequest = buildSessionFrom(sessionDto, dm)

        upsertSession(sessionRequest)
    }

    Session addMessage(Session session, String message, boolean combat) {
        if (!session) return null
        if (!message) return session

        combat ? addToCombatLog(session, message) : addToNonCombatLog(session, message)

        Auditor.enableAuditing(session)
        sessionRepository.save(session)
    }

    void deleteSession(String id) {
        sessionRepository.deleteById(id)
    }

    Session attachUserToSession(Session session, User user) {
        if (!session || !user) return null
        if (!sessionRepository.existsById(session.identifier)) return null
        if (!userRepository.existsById(user.identifier)) return null

        if (!session.players) session.players = []
        session.players << user

        Auditor.updateAuditing(session)
        sessionRepository.save(session)
    }

    Session attachCharacterToSession(Session session, Character character) {
        if (!session || !character) return null
        if (!sessionRepository.existsById(session.identifier)) return null
        if (!characterRepository.existsById(character.identifier)) return null

        if (!session.characters) session.characters = []
        session.characters << character

        session
    }

    Session moveRelationships(String oldId) {
        Session oldSession = sessionRepository.findById(oldId).orElse(null)
        if (!oldSession) return null

        Session newSession = sessionRepository.save(new Session())

        Auditor.updateAuditing(oldSession)
        Auditor.enableAuditing(newSession)
        sessionRepository.refactorRelationships(oldSession.identifier, newSession.identifier).orElse(null)
    }

    Session buildSessionFrom(SessionDto sessionDto, User dm) {
        Session session = modelMapper.map(sessionDto, Session)

        session.dm = dm

        session
    }

    SessionDto buildDtoFrom(Session session) {
        SessionDto sessionDto = modelMapper.map(session, SessionDto)

        String dmId = session.dm ? session.dm.identifier : null
        String historyId = session.history ? session.history.identifier : null
        String chatId = session.chatLog ? session.chatLog.identifier : null
        String mapId = session.map ? session.map.identifier : null
        String combatId = session.combat ? session.combat.identifier : null
        Set<String> npcIds = session.npcs ?
                session.npcs.stream().map({ npc -> npc.identifier }).collect(Collectors.toSet()) :
                null
        Set<String> playerIds = session.players ?
                session.players.stream().map({ player -> player.identifier }).collect(Collectors.toSet()) :
                null
        Set<String> characterIds = session.characters ?
                session.characters.stream().map({ character -> character.identifier }).collect(Collectors.toSet()) :
                null

        sessionDto.setDmId(dmId)
        sessionDto.setHistoryId(historyId)
        sessionDto.setChatId(chatId)
        sessionDto.setMapId(mapId)
        sessionDto.setCombatId(combatId)
        sessionDto.setNpcIds(npcIds)
        sessionDto.setPlayerIds(playerIds)
        sessionDto.setCharacterIds(characterIds)

        sessionDto
    }

    Set<SessionDto> buildDtoSetFrom(Set<Session> sessions) {
        if (!sessions) return []
        sessions.stream().map({ session -> buildDtoFrom(session) }).collect(Collectors.toSet())
    }

    private void addToCombatLog(Session session, String message) {
        session.combatLog == null ? session.combatLog = [message] : session.combatLog << message
    }

    private void addToNonCombatLog(Session session, String message) {
        session.nonCombatLog == null ? session.nonCombatLog = [message] : session.nonCombatLog << message
    }

}
