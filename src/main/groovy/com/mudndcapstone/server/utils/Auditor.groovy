package com.mudndcapstone.server.utils

import com.mudndcapstone.server.models.Node

class Auditor {

    static <T extends Node> T enableAuditing(T node) {
        node.setCreatedAt(new Date())
        node.setModifiedAt(new Date())

        node
    }

}
