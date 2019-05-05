package com.mudndcapstone.server.models

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class Messenger {
    @NotNull @JsonProperty(value = "body") String body
}
