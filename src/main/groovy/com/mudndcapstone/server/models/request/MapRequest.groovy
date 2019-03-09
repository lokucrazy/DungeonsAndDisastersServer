package com.mudndcapstone.server.models.request

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class MapRequest {
    @NotNull @JsonProperty(value = "session_id") Long sessionId
    String image
}
