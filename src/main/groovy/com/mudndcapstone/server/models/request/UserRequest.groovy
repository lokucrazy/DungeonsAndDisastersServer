package com.mudndcapstone.server.models.request

import javax.validation.constraints.NotNull

class UserRequest {
    @NotNull String username
    @NotNull String password
    Date birthdate
}