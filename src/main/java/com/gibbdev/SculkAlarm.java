package com.gibbdev;

import com.gibbdev.server.commands.SculkAlarmConfig;
import com.gibbdev.server.config.ConfigLoader;
import com.gibbdev.server.display.RecoveryCompassAction;
import com.gibbdev.server.commands.SculkAlarmAppearance;
import com.gibbdev.server.commands.SculkAlarmToggle;
import com.gibbdev.server.display.SidebarGenerator;
import com.gibbdev.server.utils.SidebarManager;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public final class SculkAlarm extends JavaPlugin {

    private static SculkAlarm plugin;

    public static NamespacedKey dataToggle;
    public static NamespacedKey dataAppearance;

    @Getter
    private final String configName = this.getDataFolder() + "/config.yml";
    @Getter
    private ConfigLoader configLoader;
    @Getter
    private SidebarManager sidebarManager;

    @Override
    public void onEnable() {
        plugin = this;
        dataToggle =  new NamespacedKey(plugin, "sa_toggle");
        dataAppearance =  new NamespacedKey(plugin, "sa_appearance");

//        saveDefaultConfig();
        this.configLoader = new ConfigLoader(this);
        this.sidebarManager = new SidebarManager(this);

        Integer regEvtListeners     = registerEventListeners();
        Integer regCommands         = registerCommands();

        this.getLogger().log(Level.INFO, "< ----- Sculk alarm loaded ----- >");
        this.getLogger().log(Level.INFO, "< registered " + regEvtListeners + " event listeners   >");
        this.getLogger().log(Level.INFO, "< registered " + regCommands + " commands          >");
        this.getLogger().log(Level.INFO, "< ------------------------------ >");

    }

    //  Registers Events Used in other classes
    private Integer registerEventListeners() {
        List<Object> eventListenersCount = new ArrayList<>();

        eventListenersCount.add(new RecoveryCompassAction(this));
        eventListenersCount.add(new SidebarGenerator(this));

        for (int i = 0; i < eventListenersCount.toArray().length; i++) {
            getServer().getPluginManager().registerEvents((Listener) eventListenersCount.get(i), this);
        }

        return eventListenersCount.toArray().length;
    }
    //  Registers commands
    private Integer registerCommands() {
        List<CommandExecutor> comandsCount = new ArrayList<>();

        comandsCount.add(new SculkAlarmAppearance(this));
        getCommand(SculkAlarmAppearance.COMMAND_ID).setExecutor(comandsCount.get(0));

        comandsCount.add(new SculkAlarmToggle(this));
        getCommand(SculkAlarmToggle.COMMAND_ID).setExecutor(comandsCount.get(1));

        comandsCount.add(new SculkAlarmConfig(this));
        getCommand(SculkAlarmConfig.COMMAND_ID).setExecutor(comandsCount.get(2));


        return comandsCount.toArray().length;
    }

    //  Plugin Getter
    public static SculkAlarm getPlugin() {
        return plugin;
    }

}