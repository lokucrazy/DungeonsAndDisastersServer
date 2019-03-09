package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.History

interface HistoryService {
    List<History> getAllHistories()
    History getHistoryById(Long id)
    void deleteHistory(Long id)
}