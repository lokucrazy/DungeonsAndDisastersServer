package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.repositories.HistoryRepository
import com.mudndcapstone.server.services.HistoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HistoryServiceImpl implements HistoryService {

    @Autowired HistoryRepository historyRepository

    @Override
    List<History> getAllHistories() {
        historyRepository.findAll().asList()
    }

    @Override
    History getHistoryById(Long id) {
        historyRepository.findById(id).orElse(null)
    }

    @Override
    void deleteHistory(Long id) {
        historyRepository.deleteById(id)
    }
}
