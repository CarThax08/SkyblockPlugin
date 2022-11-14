package com.github.carthax08.skyblockplugin;

import com.github.carthax08.skyblockplugin.util.handlers.DatabaseHandler;
import com.github.carthax08.skyblockplugin.util.managers.HandlerManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class PluginMain extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Logger logger = getLogger();
        logger.info("Initializing...");
        initCommands();
        initEvents();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        HandlerManager.dbHandler = new DatabaseHandler(getConfig().getString("settings.database.type"), getConfig().getString("settings.database.url"), getConfig().getInt("settings.database.port"), getConfig().getString("settings.database.username"), getConfig().getString("settings.database.password"), getConfig().getString("settings.database.database"));
        DatabaseHandler handler = HandlerManager.dbHandler;
        if (!handler.executeStatement("CREATE TABLE IF NOT EXISTS player-data")){
            logger.severe("CRITICAL: FAILED TO CONNECT TO DATABASE! PLEASE FIX DATABASE CONFIG AND RESTART SERVER!");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    private void initEvents() {
        //TODO: Actual events lmao
    }

    private void initCommands() {
        //TODO: Actual commands lmao
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Logger logger = getLogger();
        logger.info("Shutting down...");
    }
}
