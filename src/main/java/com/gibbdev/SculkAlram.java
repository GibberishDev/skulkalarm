package com.gibbdev;

import com.gibbdev.server.commands.TestSidebarSetup;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class SculkAlram extends JavaPlugin {
    private static SculkAlram plugin;
    @Override
    public void onEnable() {
        plugin = this;

        this.getLogger().log(Level.INFO, "Sculk alarm loaded. Thank you and enjoy");

        getCommand("test_sidebar").setExecutor(new TestSidebarSetup());
    }

    @Override
    public void onDisable() {
    }

    public static SculkAlram getPlugin() {
        return plugin;
    }
}
