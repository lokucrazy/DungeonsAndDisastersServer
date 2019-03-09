package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.NPC
import com.mudndcapstone.server.models.request.NPCRequest
import com.mudndcapstone.server.repositories.NPCRepository
import com.mudndcapstone.server.services.NPCService
import com.mudndcapstone.server.utils.ModelBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NPCServiceImpl implements NPCService {

    @Autowired NPCRepository npcRepository

    @Override
    List<NPC> getAllNPCs() {
        npcRepository.findAll().asList()
    }

    @Override
    NPC getNPCById(Long id) {
        npcRepository.findById(id).orElse(null)
    }

    @Override
    NPC createNPC(NPCRequest npcRequest) {
        NPC npc = ModelBuilder.buildNPCFrom(npcRequest)
        npcRepository.save(npc)
    }

    @Override
    void deleteNPC(Long id) {
        npcRepository.deleteById(id)
    }

}
