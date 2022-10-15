package com.gibbdev.server.commands;

import com.gibbdev.SculkAlram;
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

public class SculkAlarmToggle implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(label + " can't be run in console");
            return true;
        }

        Player p = ((Player) sender).getPlayer();

        assert p != null;
        if (p.hasPermission("sculk_alarm.toggle")) {
            PersistentDataContainer pData = p.getPersistentDataContainer();
            NamespacedKey dataKey = new NamespacedKey(SculkAlram.getPlugin(), "sa_toggle");
            if (!(args.length == 0)) {
                if      (args[0].equals("on")) { stateOn(p);}
                else if (args[0].equals("off")) { stateOff(p); }
                else    {p.sendMessage(Component.text("Invalid syntax. usage: /sa_toggle on|off", Style.style(TextColor.color(255, 120, 120))));}
            } else {
                if (pData.has(dataKey)) {
                    switch (pData.get(dataKey, PersistentDataType.INTEGER)) {
                        case 0 : stateOn(p); break;
                        case 1: stateOff(p); break;
                    }
                } else {stateOn(p);}
            }
        } else {
            p.sendMessage(Component.text("You do not have permission for this command.", Style.style(TextColor.color(255, 120, 120), TextDecoration.ITALIC, TextDecoration.UNDERLINED)));
        }

        return false;
    }

    private void stateOn(Player p) {
        PersistentDataContainer pData = p.getPersistentDataContainer();
        NamespacedKey dataKey = new NamespacedKey(SculkAlram.getPlugin(), "sa_toggle");
        pData.set(dataKey, PersistentDataType.INTEGER, 1);
        p.sendMessage(Component.text("Sculk alarm gui is now shown", Style.style(TextColor.color(120, 255 , 120))));
    }

    private void stateOff(Player p) {
        PersistentDataContainer pData = p.getPersistentDataContainer();
        NamespacedKey dataKey = new NamespacedKey(SculkAlram.getPlugin(), "sa_toggle");
        pData.set(dataKey, PersistentDataType.INTEGER, 0);
        p.sendMessage(Component.text("Sculk alarm gui is now hidden", Style.style(TextColor.color(120, 255 , 120))));
        SidebarGenerator.clearSidebar(p);
    }

}
