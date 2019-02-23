package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.NPC

interface NPCService {
    List<NPC> getAllNPCs()
    NPC getNPCById(Long id)
    NPC createNPC(NPC npc)
    void deleteNPC(Long id)
}