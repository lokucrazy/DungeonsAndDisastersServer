package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Combat

interface CombatService {
    List<Combat> getAllCombats()
    Combat getCombatById(Long id)
    Combat createCombat(Combat combat)
    void deleteCombat(Long id)
}
