package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class MapDto extends EntityDto {
    @NotNull @JsonProperty("session_id") String sessionId
    List<String> images
}
