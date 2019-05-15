package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.Required

abstract class Being extends Node {
    @Required String name
}
