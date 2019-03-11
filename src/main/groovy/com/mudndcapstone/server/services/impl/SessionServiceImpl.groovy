package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.SessionDto
import com.mudndcapstone.server.repositories.SessionRepository
import com.mudndcapstone.server.services.SessionService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class SessionServiceImpl {

    @Autowired SessionRepository sessionRepository
    @Autowired UserServiceImpl userService
    @Autowired ModelMapper modelMapper

    Set<Session> getAllSessions() {
        sessionRepository.findAll().toSet()
    }

    Session getSessionById(Long id) {
        sessionRepository.findById(id).orElse(null)
    }

    Session createSession(Session session) {
        User dm = session.dm
        if (!dm) return null

        sessionRepository.save(session)
    }

    void deleteSession(Long id) {
        sessionRepository.deleteById(id)
    }

    Session buildSessionFrom(SessionDto sessionDto) {
        Session session = modelMapper.map(sessionDto, Session)

        User dm = userService.getUserById(sessionDto.dmId)

        session.setDm(dm)

        session
    }

    SessionDto buildDtoFrom(Session session) {
        SessionDto sessionDto = modelMapper.map(session, SessionDto)

        Long dmId = session.dm ? session.dm.identifier : null
        Long historyId = session.history ? session.history.identifier : null
        Long chatId = session.chatLog ? session.chatLog.identifier : null
        Long mapId = session.mapList ? session.mapList.identifier : null
        Long combatId = session.combatList ? session.combatList.identifier : null
        HashSet<Long> npcIds = session.npcs ?
                session.npcs.stream().map({ npc -> npc.identifier }).collect(Collectors.toSet()) as HashSet :
                null
        HashSet<Long> playerIds = session.players ?
                session.players.stream().map({ player -> player.identifier }).collect(Collectors.toSet()) as HashSet :
                null
        HashSet<Long> characterIds = session.characters ?
                session.characters.stream().map({ character -> character.identifier }).collect(Collectors.toSet()) as HashSet :
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
        sessions.stream().map({ session -> buildDtoFrom(session) }).collect(Collectors.toSet())
    }

}
