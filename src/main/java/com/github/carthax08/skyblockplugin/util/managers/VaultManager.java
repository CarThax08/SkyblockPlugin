package com.github.carthax08.skyblockplugin.util.managers;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultManager {
    public static Economy economyProvider;

    public static void setBalance(double target, Player player) {
        double currentBalance = economyProvider.getBalance(player);
        double difference = target - currentBalance;
        if(difference > 0){
            economyProvider.depositPlayer(player, difference);
        }else{
            economyProvider.withdrawPlayer(player, -difference);
        }
    }

    public void init(){
        economyProvider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class).getProvider();
    }
}
