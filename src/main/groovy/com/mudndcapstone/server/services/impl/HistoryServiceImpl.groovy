package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.models.dto.HistoryDto
import com.mudndcapstone.server.repositories.HistoryRepository
import com.mudndcapstone.server.services.HistoryService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class HistoryServiceImpl {

    @Autowired HistoryRepository historyRepository
    @Autowired ModelMapper modelMapper

    Set<History> getAllHistories() {
        historyRepository.findAll().toSet()
    }

    History getHistoryById(Long id) {
        historyRepository.findById(id).orElse(null)
    }

    void deleteHistory(Long id) {
        historyRepository.deleteById(id)
    }

    History buildHistoryFrom(HistoryDto historyDto) {
        History history = modelMapper.map(historyDto, History)

        history
    }

    HistoryDto buildDtoFrom(History history) {
        HistoryDto historyDto = modelMapper.map(history, HistoryDto)

        Long historyId = history.history ? history.history.identifier : null

        historyDto.setHistoryId(historyId)

        historyDto
    }

    Set<HistoryDto> buildDtoSetFrom(Set<History> histories) {
        histories.stream().map({ history -> buildDtoFrom(history) }).collect(Collectors.toSet())
    }

}
