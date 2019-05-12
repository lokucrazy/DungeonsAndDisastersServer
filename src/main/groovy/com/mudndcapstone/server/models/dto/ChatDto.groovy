package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class ChatDto extends EntityDto {
    @JsonProperty("session_id") @NotNull String sessionId
    List<String> log
    String note
}
