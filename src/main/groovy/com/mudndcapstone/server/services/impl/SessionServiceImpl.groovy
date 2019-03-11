package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.SessionDto
import com.mudndcapstone.server.repositories.SessionRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class SessionServiceImpl {

    @Autowired SessionRepository sessionRepository
    @Autowired UserServiceImpl userService
    @Autowired ModelMapper modelMapper

    List<Session> getAllSessions() {
        sessionRepository.findAll().asList()
    }

    Session getSessionById(String id) {
        sessionRepository.findById(id).orElse(null)
    }

    Session createSession(Session session) {
        User dm = session.dm
        if (!dm) return null

        sessionRepository.save(session)
    }

    void deleteSession(String id) {
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

        String dmId = session.dm ? session.dm.identifier : null
        String historyId = session.history ? session.history.identifier : null
        String chatId = session.chatLog ? session.chatLog.identifier : null
        String mapId = session.mapList ? session.mapList.identifier : null
        String combatId = session.combatList ? session.combatList.identifier : null
        List<String> npcIds = session.npcs ?
                session.npcs.stream().map({ npc -> npc.identifier }).collect(Collectors.toList()) :
                null
        List<String> playerIds = session.players ?
                session.players.stream().map({ player -> player.identifier }).collect(Collectors.toList()) :
                null
        List<String> characterIds = session.characters ?
                session.characters.stream().map({ character -> character.identifier }).collect(Collectors.toList()) :
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

    List<SessionDto> buildDtoListFrom(List<Session> sessions) {
        sessions.stream().map({ session -> buildDtoFrom(session) }).collect(Collectors.toList())
    }

}
