package com.github.carthax08.skyblockplugin;

import com.github.carthax08.skyblockplugin.commands.IslandCommand;
import com.github.carthax08.skyblockplugin.util.handlers.DatabaseHandler;
import com.github.carthax08.skyblockplugin.util.managers.HandlerManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class PluginMain extends JavaPlugin {

    Logger logger;
    private static PluginMain instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        logger = getLogger();
        logger.info("Initializing...");
        initCommands();
        initEvents();
        initHandlers();
        initDatabase();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    private void initDatabase() {
        DatabaseHandler handler = HandlerManager.dbHandler;
        if (!handler.executeStatement("CREATE TABLE IF NOT EXISTS playerdata(uuid varchar(36), money double, islandID int, username varchar(16),  KEY(uuid))") || !handler.executeStatement("CREATE TABLE IF NOT EXISTS islands(id int, x int, y int, z int, world varchar(64), level_xp bigint, KEY(id))")){
            logger.severe("CRITICAL: FAILED TO CONNECT TO DATABASE OR CREATE TABLES! PLEASE CHECK DATABASE CONFIG AND RESTART SERVER!");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    private void initHandlers() {
        HandlerManager.dbHandler = new DatabaseHandler(getConfig().getString("settings.database.type"), getConfig().getString("settings.database.url"), getConfig().getInt("settings.database.port"), getConfig().getString("settings.database.username"), getConfig().getString("settings.database.password"), getConfig().getString("settings.database.database"));
    }

    private void initEvents() {
        //TODO: Actual events lmao
    }

    private void initCommands() {
        //TODO: Actual commands lmao
        getCommand("island").setExecutor(new IslandCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Logger logger = getLogger();
        logger.info("Shutting down...");
    }

    public static PluginMain getInstance(){
        return instance;
    }
}
