package com.mudndcapstone.server.models

import javax.validation.constraints.NotNull

abstract class Being extends Node {
    @NotNull String name
}
