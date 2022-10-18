package com.gibbdev.server.commands;

import com.gibbdev.SculkAlarm;
import com.gibbdev.server.display.SidebarGenerator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class SculkAlarmAppearance implements CommandExecutor {
    public String COMMAND_ID = "sa_appearance";
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(label + " can't be run in console");
            return true;
        }

        Player p = ((Player) sender).getPlayer();

        assert p != null;
        if (p.hasPermission("sculk_alarm.appearance")) {
            PersistentDataContainer pData = p.getPersistentDataContainer();
            NamespacedKey dataKey = new NamespacedKey(SculkAlarm.getPlugin(), "sa_appearance");
            if (!(args.length == 0)) {
                if (args[0].equals("chat") || args[0].equals("sidebar")) {
                    pData.set(dataKey, PersistentDataType.STRING, args[0]);
                    p.sendMessage(Component.text("Sculk alarm gui now shows in " + args[0], Style.style(TextColor.color(120, 255 , 120))));
                    if (args[0].equals("chat")) {
                        SidebarGenerator.clearSidebar(p);
                    }
                } else {
                    p.sendMessage(Component.text("Invalid syntax. usage: /sa_appearance chat|sidebar", Style.style(TextColor.color(255, 120, 120))));
                }
            } else {
                p.sendMessage(Component.text("Invalid syntax. usage: /sa_appearance chat|sidebar", Style.style(TextColor.color(255, 120, 120))));
            }
        } else {
            p.sendMessage(Component.text("You do not have permission for this command.", Style.style(TextColor.color(255, 120, 120), TextDecoration.ITALIC, TextDecoration.UNDERLINED)));
        }

        return false;
    }
}
