package com.mudndcapstone.server.models.dto

import javax.validation.constraints.NotNull

class BeingDto extends EntityDto {
    @NotNull String name
}
