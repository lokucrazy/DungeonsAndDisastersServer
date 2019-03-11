package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class SessionDto extends EntityDto {
    String identifier
    @JsonProperty(value = "dm_id") @NotNull String dmId
    @JsonProperty(value = "non_combat_log") List<String> nonCombatLog
    @JsonProperty(value = "combat_log") List<String> combatLog
    @JsonProperty(value = "date_ended") Date dateEnded
    @JsonProperty(value = "history_id") String historyId
    @JsonProperty(value = "chat_id") String chatId
    @JsonProperty(value = "map_id") String mapId
    @JsonProperty(value = "combat_id") String combatId
    @JsonProperty(value = "npc_ids") List<String> npcIds
    @JsonProperty(value = "player_ids") List<String> playerIds
    @JsonProperty(value = "character_ids") List<String> characterIds
}