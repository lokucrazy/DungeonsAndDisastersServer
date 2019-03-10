package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
class Map extends Node {
    List<String> images

    @Relationship(type = "HAS_MAP_LIST", direction = Relationship.INCOMING)
    Session session
}
