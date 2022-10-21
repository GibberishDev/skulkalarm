package com.gibbdev.server.display;

import com.gibbdev.SculkAlarm;
import com.gibbdev.server.config.ConfigLoader;
import com.gibbdev.server.utils.SidebarManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SidebarGenerator implements Listener {
    private SculkAlarm plugin;

    public SidebarGenerator(SculkAlarm plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLogOff(PlayerQuitEvent e) {
        this.plugin.getSidebarManager().clearSidebar(e.getPlayer());
    }

}
