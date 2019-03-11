package com.mudndcapstone.server.services.impl

import com.mudndcapstone.server.models.History
import com.mudndcapstone.server.models.dto.HistoryDto
import com.mudndcapstone.server.repositories.HistoryRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class HistoryServiceImpl {

    @Autowired HistoryRepository historyRepository
    @Autowired ModelMapper modelMapper

    List<History> getAllHistories() {
        historyRepository.findAll().asList()
    }

    History getHistoryById(String id) {
        historyRepository.findById(id).orElse(null)
    }

    void deleteHistory(String id) {
        historyRepository.deleteById(id)
    }

    History buildHistoryFrom(HistoryDto historyDto) {
        History history = modelMapper.map(historyDto, History)
        history
    }

    HistoryDto buildDtoFrom(History history) {
        HistoryDto historyDto = modelMapper.map(history, HistoryDto)

        String historyId = history.history ? history.history.identifier : null
        historyDto.setHistoryId(historyId)

        historyDto
    }

    List<HistoryDto> buildDtoListFrom(List<History> histories) {
        histories.stream().map({ history -> buildDtoFrom(history) }).collect(Collectors.toList())
    }

}
