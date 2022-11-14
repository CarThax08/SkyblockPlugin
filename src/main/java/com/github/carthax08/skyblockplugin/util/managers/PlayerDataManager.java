package com.github.carthax08.skyblockplugin.util.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PlayerDataManager {
    private static HashMap<Player, Location> islandLocationMap = new HashMap<>();

    public static boolean loadPlayerData(Player player) {

        try(ResultSet results = HandlerManager.dbHandler.executeStatementWithResults("SELECT * FROM playerdata WHERE uuid = '" + player.getUniqueId() + "'")){
            try(ResultSet resultsIS = HandlerManager.dbHandler.executeStatementWithResults("SELECT * FROM islands WHERE islandID = " + results.getInt("islandID"))){
                double islandX = resultsIS.getInt("x");
                double islandY = resultsIS.getInt("y");
                double islandZ = resultsIS.getInt("z");
                String islandWorld = resultsIS.getString("world");

                islandLocationMap.put(player, new Location(Bukkit.getWorld(islandWorld), islandX, islandY, islandZ));
            }
            VaultManager.setBalance(results.getDouble("money"), player);
            return true;
        } catch (SQLException e) {
            return false;
        }

    }
}
