package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

class HistoryDto extends EntityDto {
    @JsonProperty("non_combat_log") List<String> nonCombatLog
    @JsonProperty("combat_log") List<String> combatLog
    @JsonProperty("date_ended") Date dateEnded
    @JsonProperty("history_id") String historyId
}
