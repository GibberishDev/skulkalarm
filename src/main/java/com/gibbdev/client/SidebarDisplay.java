package com.gibbdev.client;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

public class SidebarDisplay {
    public static void updateAlarmSidebar(@NotNull Player player, @NotNull Integer warningLevel, @NotNull Integer secsTillLevelDrop) {

        ScoreboardManager mngr = Bukkit.getScoreboardManager();
        Scoreboard playerBoard = mngr.getNewScoreboard();
        Objective playerObjective = playerBoard.registerNewObjective("alarmSidebar", "dummy", Component.text("Sculk Alarm", Style.style(TextDecoration.BOLD, TextColor.fromHexString("#b00b69"))));
        playerObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
        playerObjective.displayName(Component.text("Sculk Alarm", Style.style(TextDecoration.BOLD, TextColor.fromHexString("#b00b69"))));
        Score warningLevelDisplay = playerObjective.getScore(ChatColor.GREEN + "Current alarm level:    ");
        warningLevelDisplay.setScore(4);
        Score warningLevelDisplay1 = playerObjective.getScore(ChatColor.GREEN + " - "  + ChatColor.GOLD + warningLevel);
        warningLevelDisplay1.setScore(3);
        Score warningSinceTiksDisplay = playerObjective.getScore(ChatColor.GREEN + "Seconds until alarm");
        warningSinceTiksDisplay.setScore(2);
        Score warningSinceTiksDisplay1 = playerObjective.getScore(ChatColor.GREEN + "level decreases:        ");
        warningSinceTiksDisplay1.setScore(1);
        Score warningSinceTiksDisplay2 = playerObjective.getScore(ChatColor.GREEN + " - " + ChatColor.GOLD + secsTillLevelDrop + "      ");
        warningSinceTiksDisplay2.setScore(0);

        player.setScoreboard(playerBoard);
    }
}
