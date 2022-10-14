package com.gibbdev;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class sculkalram extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getLogger().log(Level.INFO, "Sculk alarm loaded. Thank you and enjoy");
    }

    @Override
    public void onDisable() {
    }
}
