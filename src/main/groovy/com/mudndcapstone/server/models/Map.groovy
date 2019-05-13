package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import org.neo4j.ogm.annotation.Required

@NodeEntity
class Map extends Node {
    List<String> images

    @Required
    @Relationship(type = "HAS_MAP_LIST", direction = Relationship.INCOMING)
    Session session
}
