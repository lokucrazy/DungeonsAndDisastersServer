package com.mudndcapstone.server.utils

import com.mudndcapstone.server.models.Node

class Auditor {

    static <T extends Node> T enableAuditing(T node) {
        node.setCreatedOn(new Date())
        node.setLastModifiedOn(new Date())

        node
    }

    static <T extends Node> T updateAuditing(T node) {
        node.setLastModifiedOn(new Date())

        node
    }

}
