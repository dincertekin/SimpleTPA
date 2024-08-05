package com.dincertekin.simpleTPA;

import com.dincertekin.simpleTPA.commands.TPA;
import com.dincertekin.simpleTPA.commands.TPAccept;
import com.dincertekin.simpleTPA.commands.TPDeny;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleTPA extends JavaPlugin {

    static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        getCommand("tpa").setExecutor(new TPA());
        getCommand("tpaccept").setExecutor(new TPAccept());
        getCommand("tpdeny").setExecutor(new TPDeny());

        getLogger().info("Plugin enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled.");
    }

    public static Plugin getInstance() {
        return instance;
    }
}
