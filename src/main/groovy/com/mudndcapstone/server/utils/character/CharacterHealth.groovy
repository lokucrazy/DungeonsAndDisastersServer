package com.mudndcapstone.server.utils.character

import com.fasterxml.jackson.annotation.JsonProperty

class CharacterHealth {
    @JsonProperty("current_hit_points") int currentHitPoints
    @JsonProperty("maximum_hit_points") int maximumHitPoints
    @JsonProperty("temporary_hit_points") int temporaryHitPoints
    @JsonProperty("hit_dice") int hitDice
    @JsonProperty("success_death_saves") int successDeathSaves
    @JsonProperty("failure_death_saves") int failureDeathSaves

    CharacterHealth() {}
    CharacterHealth(int chp, int mhp, int thp, int hd, int sds, int fds) {
        currentHitPoints = chp
        maximumHitPoints = mhp
        temporaryHitPoints = thp
        hitDice = hd
        successDeathSaves = sds
        failureDeathSaves = fds
    }
}
