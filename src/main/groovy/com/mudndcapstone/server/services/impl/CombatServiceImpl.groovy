package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.Combat
import com.mudndcapstone.server.models.request.CombatRequest
import com.mudndcapstone.server.repositories.CombatRepository
import com.mudndcapstone.server.services.CombatService
import com.mudndcapstone.server.utils.ModelBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CombatServiceImpl implements CombatService {

    @Autowired CombatRepository combatRepository

    @Override
    List<Combat> getAllCombats() {
        combatRepository.findAll().asList()
    }

    @Override
    Combat getCombatById(Long id) {
        combatRepository.findById(id).orElse(null)
    }

    @Override
    Combat createCombat(CombatRequest combatRequest) {
        Combat combat = ModelBuilder.buildCombatFrom(combatRequest)
        combatRepository.save(combat)
    }

    @Override
    void deleteCombat(Long id) {
        combatRepository.deleteById(id)
    }
}
