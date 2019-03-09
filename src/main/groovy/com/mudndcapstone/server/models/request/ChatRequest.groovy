package com.mudndcapstone.server.models.request

import javax.validation.constraints.NotNull

class ChatRequest {
    @NotNull Long sessionId
    @NotNull Long userId
    @NotNull String message
}
