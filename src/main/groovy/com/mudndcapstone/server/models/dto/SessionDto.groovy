package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class SessionDto extends EntityDto {
    @JsonProperty("session_state") boolean running
    @JsonProperty("dm_id") @NotNull String dmId
    @JsonProperty("non_combat_log") List<String> nonCombatLog
    @JsonProperty("combat_log") List<String> combatLog
    @JsonProperty("date_ended") Date dateEnded
    @JsonProperty("history_id") String historyId
    @JsonProperty("chat_id") String chatId
    @JsonProperty("map_id") String mapId
    @JsonProperty("combat_id") String combatId
    @JsonProperty("npc_ids") Set<String> npcIds
    @JsonProperty("player_ids") Set<String> playerIds
    @JsonProperty("character_ids") Set<String> characterIds
}
