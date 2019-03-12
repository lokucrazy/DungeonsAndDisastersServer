package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.NPC
import com.mudndcapstone.server.models.dto.NPCDto
import com.mudndcapstone.server.repositories.NPCRepository
import com.mudndcapstone.server.services.NPCService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class NPCServiceImpl {

    @Autowired NPCRepository npcRepository
    @Autowired ModelMapper modelMapper

    Set<NPC> getAllNPCs() {
        npcRepository.findAll().toSet()
    }

    NPC getNPCById(Long id) {
        npcRepository.findById(id).orElse(null)
    }

    NPC createNPC(NPC npc) {
        npcRepository.save(npc)
    }

    void deleteNPC(Long id) {
        npcRepository.deleteById(id)
    }

    NPC buildNPCFrom(NPCDto npcDto) {
        NPC npc = modelMapper.map(npcDto, NPC)

        npc
    }

    NPCDto buildDtoFrom(NPC npc) {
        NPCDto npcDto = modelMapper.map(npc, NPCDto)

        Long sessionId = npc.session ? npc.session.identifier : null
        Long dmId = npc.dm ? npc.dm.identifier : null

        npcDto.setSessionId(sessionId)
        npcDto.setDmId(dmId)

        npcDto
    }

    Set<NPCDto> buildDtoSetFrom(Set<NPC> npcs) {
        npcs.stream().map({ npc -> buildDtoFrom(npc) }).collect(Collectors.toSet())
    }

}
