package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.NPC
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.User
import com.mudndcapstone.server.models.dto.NPCDto
import com.mudndcapstone.server.repositories.NPCRepository
import com.mudndcapstone.server.utils.Auditor
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class NPCService {

    @Autowired NPCRepository npcRepository
    @Autowired ModelMapper modelMapper

    Set<NPC> getAllNPCs() {
        npcRepository.findAll().toSet()
    }

    NPC getNPCById(String id) {
        npcRepository.findById(id).orElse(null)
    }

    NPC upsertNPC(NPC npc) {
        Auditor.enableAuditing(npc)
        npcRepository.save(npc)
    }

    NPC buildAndCreateNPC(NPCDto npcDto, Session session, User dm) {
        NPC npcRequest = buildNPCFrom(npcDto, session, dm)
        NPC npc = upsertNPC(npcRequest)
        npc
    }

    void deleteNPC(String id) {
        npcRepository.deleteById(id)
    }

    NPC buildNPCFrom(NPCDto npcDto, Session session, User dm) {
        NPC npc = modelMapper.map(npcDto, NPC)

        npc.session = session
        npc.dm = dm

        npc
    }

    NPCDto buildDtoFrom(NPC npc) {
        NPCDto npcDto = modelMapper.map(npc, NPCDto)

        String sessionId = npc.session ? npc.session.identifier : null
        String dmId = npc.dm ? npc.dm.identifier : null
        npcDto.setSessionId(sessionId)
        npcDto.setDmId(dmId)

        npcDto
    }

    Set<NPCDto> buildDtoSetFrom(Set<NPC> npcs) {
        if (!npcs) return []
        npcs.stream().map({ npc -> buildDtoFrom(npc) }).collect(Collectors.toSet())
    }

}
