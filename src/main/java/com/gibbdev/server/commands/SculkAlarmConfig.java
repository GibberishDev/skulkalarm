package com.gibbdev.server.commands;

import com.gibbdev.SculkAlarm;
import com.jowcey.ExaltedCore.base.visual.Text;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class SculkAlarmConfig implements CommandExecutor {
    private SculkAlarm plugin;

    public SculkAlarmConfig(SculkAlarm plugin) {
        this.plugin = plugin;
    }

    public static String COMMAND_ID = "sa_config";
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("sculk_alarm.config")) {
            Map<String, Object> data = this.plugin.getConfigLoader().getConfigData().toMap();
            Object obj = data.get(args[0]);
            if (obj == null)
                sender.sendMessage(Text.color("&cConfig name not found!"));
            if (obj instanceof Integer) {
                try {
                    data.put(args[0], Integer.parseInt(args[1]));
                } catch (NumberFormatException e) {
                    data.put(args[0], obj);
                }
            } else if (obj instanceof Boolean) {
                data.put(args[0], args[1].equalsIgnoreCase("true"));
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    sb.append(args[i]).append(" ");
                }
                data.put(args[0], sb.toString().trim());
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                sb.append(args[i]).append(" ");
            }
            sender.sendMessage(Text.color("&e'" + args[0] + "' &ais now set to: '" + sb + "&r&a'"));
            this.plugin.getConfigLoader().getConfigData().fromMap(data);
            this.plugin.getConfigLoader().save();
        } else {
            sender.sendMessage(Component.text("You do not have permission for this command.", Style.style(TextColor.color(255, 120, 120), TextDecoration.ITALIC, TextDecoration.UNDERLINED)));
        }

        return false;
    }
}

