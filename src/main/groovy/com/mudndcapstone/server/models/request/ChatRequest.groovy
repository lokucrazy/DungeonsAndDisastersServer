package com.mudndcapstone.server.models.request

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class ChatRequest {
    @NotNull @JsonProperty(value = "session_id") Long sessionId
    @NotNull @JsonProperty(value = "user_id") Long userId
    @NotNull String message
}
