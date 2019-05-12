package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

class EntityDto {
    String identifier
    @JsonProperty("created_on") Date createdOn
    @JsonProperty("last_modified_on") Date lastModifiedOn
}
