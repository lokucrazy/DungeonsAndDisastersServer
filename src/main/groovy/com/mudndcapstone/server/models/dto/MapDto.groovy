package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class MapDto extends EntityDto {
    @NotNull @JsonProperty(value = "session_id") Long sessionId
    List<String> images
}
