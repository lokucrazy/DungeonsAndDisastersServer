package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.request.CombatRequest

interface CombatService {
    List<Combat> getAllCombats()
    Combat getCombatById(Long id)
    Combat createCombat(CombatRequest combat)
    void deleteCombat(Long id)
}
