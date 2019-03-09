package com.mudndcapstone.server.models

import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
class Chat extends Node {
    List<String> log
    String note

    @Relationship(type = "HAS_CHAT_LOG", direction = Relationship.INCOMING)
    Session session
}
