package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.DM
import com.mudndcapstone.server.repositories.DMRepository
import com.mudndcapstone.server.services.DMService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DMServiceImpl implements DMService {

    @Autowired DMRepository dmRepository

    @Override
    List<DM> getAllDMs() {
        dmRepository.findAllDMs().asList()
    }

    @Override
    DM getDMById(Long id) {
        dmRepository.findDMById(id).orElse(null)
    }

    @Override
    DM createDM(DM dm) {
        dmRepository.save(dm)
    }

    @Override
    void deleteDM(Long id) {
        dmRepository.deleteById(id)
    }
}
