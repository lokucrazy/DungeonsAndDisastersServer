package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class ChatDto extends EntityDto {
    @NotNull @JsonProperty(value = "session_id") String sessionId
    List<String> log
    String note
}
