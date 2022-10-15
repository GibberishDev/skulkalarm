package com.gibbdev.server.display;

import com.gibbdev.SculkAlram;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

public class SidebarGenerator {
    static ScoreboardManager mngr = Bukkit.getScoreboardManager();
    static Scoreboard playerBoard = mngr.getNewScoreboard();
    static Objective playerObjective = playerBoard.registerNewObjective("alarmSidebar", "dummy", Component.text("Sculk Alarm", Style.style(TextDecoration.BOLD, TextColor.fromHexString("#b00b69"))));

    static Scoreboard prevScoreboard;
    static Boolean scoreboardEnabled = false;
    private static void updateAlarmSidebar(@NotNull Player p) {
        playerBoard = mngr.getNewScoreboard();
        playerObjective.unregister();
        playerObjective = playerBoard.registerNewObjective("alarmSidebar", "dummy", Component.text("Sculk Alarm", Style.style(TextDecoration.BOLD, TextColor.fromHexString("#b00b69"))));
        Integer warningLevel = PlayerDataScraper.getAlarmLevel(p);
        Integer secsTillLevelDrop = PlayerDataScraper.getAlarmSecs(p);
        playerObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
//        TODO: Add custom characters support and colours for different alarm levels
        Score warningLevelDisplay = playerObjective.getScore(ChatColor.GREEN + "Current alarm level:    ");
        warningLevelDisplay.setScore(10);
        Score warningLevelDisplay1 = playerObjective.getScore(ChatColor.GREEN + " - "  + ChatColor.GOLD + warningLevel);
        warningLevelDisplay1.setScore(9);
        Score warningSinceTiksDisplay = playerObjective.getScore(ChatColor.GREEN + "Seconds until alarm");
        warningSinceTiksDisplay.setScore(8);
        Score warningSinceTiksDisplay1 = playerObjective.getScore(ChatColor.GREEN + "level decreases:        ");
        warningSinceTiksDisplay1.setScore(7);
        Score warningSinceTiksDisplay2 = playerObjective.getScore(ChatColor.GREEN + " - " + ChatColor.GOLD + secsTillLevelDrop + "      ");
        warningSinceTiksDisplay2.setScore(6);
        p.setScoreboard(playerBoard);
    }

    public static void toggleSidebar(@NotNull Player p) {
        Scoreboard currentScoreboard = p.getScoreboard();
        if (currentScoreboard.equals(playerBoard)) {
            p.setScoreboard(prevScoreboard);
            scoreboardEnabled = false;
        } else {
            prevScoreboard = currentScoreboard;
            p.setScoreboard(playerBoard);
            scoreboardEnabled = true;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if (scoreboardEnabled) {
                    updateAlarmSidebar(p);
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(SculkAlram.getPlugin(),0L, 20L);
    }

    public static void clearSidebar(@NotNull Player p) {
        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        scoreboardEnabled = false;
//        TODO: Make it replace back to previous sidebar
    }
}
