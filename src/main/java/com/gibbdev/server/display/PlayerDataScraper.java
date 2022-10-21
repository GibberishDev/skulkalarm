package com.gibbdev.server.display;

import com.jowcey.ExaltedCore.base.dependencies.nbt.NBTEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerDataScraper {

    public static Integer getWarningLevel(@NotNull Player p) {
        NBTEntity playerNBT = new NBTEntity(p.getPlayer());
        return playerNBT.getCompound("warden_spawn_tracker").getInteger("warning_level");
    }

    public static Integer getAlarmSecs(@NotNull Player p) {
        NBTEntity playerNBT = new NBTEntity(p.getPlayer());
        return (int) Math.round((12000 - playerNBT.getCompound("warden_spawn_tracker").getInteger("ticks_since_last_warning")) * 0.05);
    }

}
