package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

class EntityDto {
    String identifier
    @JsonProperty(value = "created_at") Date createdAt
    @JsonProperty(value = "modifier_at") Date modifiedAt
}
