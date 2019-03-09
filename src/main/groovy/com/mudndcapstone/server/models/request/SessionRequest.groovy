package com.mudndcapstone.server.models.request

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class SessionRequest {
    @JsonProperty(value = "dm_id") @NotNull Long dmId
}
