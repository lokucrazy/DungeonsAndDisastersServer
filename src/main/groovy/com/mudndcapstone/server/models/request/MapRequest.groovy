package com.mudndcapstone.server.models.request

import javax.validation.constraints.NotNull

class MapRequest {
    @NotNull Long sessionId
    String image
}
