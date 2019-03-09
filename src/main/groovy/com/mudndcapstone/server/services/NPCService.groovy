package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.NPC
import com.mudndcapstone.server.models.request.NPCRequest

interface NPCService {
    List<NPC> getAllNPCs()
    NPC getNPCById(Long id)
    NPC createNPC(NPCRequest npcRequest)
    void deleteNPC(Long id)
}