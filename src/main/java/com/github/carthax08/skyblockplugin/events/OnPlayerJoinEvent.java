package com.github.carthax08.skyblockplugin.events;

import com.github.carthax08.skyblockplugin.util.managers.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if(!PlayerDataManager.loadPlayerData(event.getPlayer())){
            event.getPlayer().kickPlayer("There was an error loading your player data. Please rejoin \n If the issue persists, please report it to a staff member");
        }
    }
}
