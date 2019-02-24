package com.mudndcapstone.server.repositories

import com.mudndcapstone.server.models.DM
import org.springframework.data.neo4j.annotation.Query
import org.springframework.stereotype.Repository

@Repository
interface DMRepository extends UserRepository {
    /* can't use default repo, since DM is not an actual node..
    can either extend the user repo or just use manual queries here
    */

    @Query("MATCH (dm:DM) RETURN dm")
    Iterable<DM> findAllDMs()

    @Query("MATCH (dm:DM) WHERE id(dm)={0} RETURN dm")
    Optional<DM> findDMById(Long id)
}