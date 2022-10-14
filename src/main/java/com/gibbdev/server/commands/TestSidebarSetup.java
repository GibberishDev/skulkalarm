package com.gibbdev.server.commands;

import com.gibbdev.client.SidebarDisplay;
import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class TestSidebarSetup implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(label + " can't be run in console");
            return true;
        }
        Player player = ((Player) sender).getPlayer();
        assert player != null;
        NBTEntity playerNBT = new NBTEntity(player);
        Integer warningLevel = playerNBT.getCompound("warden_spawn_tracker").getInteger("warning_level");
        Integer ticksSinceLastWarning = playerNBT.getCompound("warden_spawn_tracker").getInteger("ticks_since_last_warning");
        SidebarDisplay.updateAlarmSidebar(player, warningLevel, (int) Math.round((12000 - ticksSinceLastWarning) * 0.05));
        return true;
    }
}
