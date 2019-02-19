package com.mudndcapstone.server.repositories

import org.springframework.stereotype.Repository

@Repository
interface DMRepository {
    /* can't use default repo, since DM is not an actual node..
    can either extend the user repo or just use manual queries here
    */
}