package com.gibbdev;

import com.gibbdev.server.display.RecoveryCompassAction;
import com.gibbdev.server.commands.SculkAlarmAppearance;
import com.gibbdev.server.commands.SculkAlarmToggle;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class SculkAlram extends JavaPlugin {
    private static SculkAlram plugin;
    @Override
    public void onEnable() {
        plugin = this;

        this.getLogger().log(Level.INFO, "Sculk alarm loaded. Thank you and enjoy");

        getServer().getPluginManager().registerEvents(new RecoveryCompassAction(), this);

        getCommand("sa_appearance").setExecutor(new SculkAlarmAppearance());
        getCommand("sa_toggle").setExecutor(new SculkAlarmToggle());
    }

    @Override
    public void onDisable() {
    }

    public static SculkAlram getPlugin() {
        return plugin;
    }
}
//TODO: Add config support for enabling and disabling RP and custom messages and characters