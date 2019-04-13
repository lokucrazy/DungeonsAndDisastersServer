package com.mudndcapstone.server.utils

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class Messenger {
    @NotNull @JsonProperty(value = "message") String message
}
