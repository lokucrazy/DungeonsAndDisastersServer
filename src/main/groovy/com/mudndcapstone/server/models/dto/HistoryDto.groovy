package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

class HistoryDto extends EntityDto {
    @JsonProperty(value = "non_combat_log") List<String> nonCombatLog
    @JsonProperty(value = "combat_log") List<String> combatLog
    @JsonProperty(value = "date_ended") Date dateEnded
    @JsonProperty(value = "history_id") String historyId
}
