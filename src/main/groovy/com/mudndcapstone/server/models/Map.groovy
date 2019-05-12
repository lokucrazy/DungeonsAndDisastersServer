package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

import javax.validation.constraints.NotNull

@NodeEntity
class Map extends Node {
    List<String> images

    @NotNull
    @Relationship(type = "HAS_MAP_LIST", direction = Relationship.INCOMING)
    Session session
}
