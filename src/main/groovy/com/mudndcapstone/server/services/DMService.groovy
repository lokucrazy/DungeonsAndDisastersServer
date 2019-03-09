package com.mudndcapstone.server.services

import com.mudndcapstone.server.models.DM

interface DMService {
    List<DM> getAllDMs()
    DM getDMById(Long id)
}