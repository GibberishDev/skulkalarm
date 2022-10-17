package com.gibbdev;

import com.gibbdev.server.config.ConfigLoader;
import com.gibbdev.server.display.RecoveryCompassAction;
import com.gibbdev.server.commands.SculkAlarmAppearance;
import com.gibbdev.server.commands.SculkAlarmToggle;
import com.gibbdev.server.display.SidebarGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class SculkAlram extends JavaPlugin {

    private static SculkAlram plugin;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();
        ConfigLoader.loadConfig();

        Integer regEvtListeners     = registerEventListeners();
        Integer regCommands         = registerCommands();

        this.getLogger().log(Level.INFO, "< ----- Sculk alarm loaded ----- >");
        this.getLogger().log(Level.INFO, "< registrated " + regEvtListeners + " event listeners  >");
        this.getLogger().log(Level.INFO, "< registrated " + regCommands + " commands         >");
        this.getLogger().log(Level.INFO, "< ------------------------------ >");

    }

    //  Registers Events Used in other classes
    private Integer registerEventListeners() {
        Integer eventListenersCount = 0;

        getServer().getPluginManager().registerEvents(new RecoveryCompassAction(), this);    eventListenersCount++;
        getServer().getPluginManager().registerEvents(new SidebarGenerator(), this);    eventListenersCount++;

        return eventListenersCount;
    }
    //  Registers commands
    private Integer registerCommands() {
        Integer comandsCount = 0;

        getCommand("sa_appearance").setExecutor(new SculkAlarmAppearance());    comandsCount++;
        getCommand("sa_toggle").setExecutor(new SculkAlarmToggle());            comandsCount++;

        return comandsCount;
    }

    //  Plugin Getter
    public static SculkAlram getPlugin() {
        return plugin;
    }

}
//TODO: Add config support for enabling and disabling RP and custom messages and characters