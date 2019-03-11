package com.mudndcapstone.server.models.dto

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.NotNull

class SessionDto extends EntityDto {
    Long identifier
    @JsonProperty(value = "dm_id") @NotNull Long dmId
    @JsonProperty(value = "non_combat_log") List<String> nonCombatLog
    @JsonProperty(value = "combat_log") List<String> combatLog
    @JsonProperty(value = "date_ended") Date dateEnded
    @JsonProperty(value = "history_id") Long historyId
    @JsonProperty(value = "chat_id") Long chatId
    @JsonProperty(value = "map_id") Long mapId
    @JsonProperty(value = "combat_id") Long combatId
    @JsonProperty(value = "npc_ids") HashSet<Long> npcIds
    @JsonProperty(value = "player_ids") HashSet<Long> playerIds
    @JsonProperty(value = "character_ids") HashSet<Long> characterIds
}