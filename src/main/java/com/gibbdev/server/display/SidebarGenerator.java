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
        Score warningLevelDisplay = playerObjective.getScore(ChatColor.GREEN + "Current alarm level:    ");

        String warning_text = "";
        switch (warningLevel) {
            case 0: warning_text = ChatColor.GREEN + " - "  + ChatColor.BOLD + ChatColor.GREEN + warningLevel; break;
            case 1: warning_text = ChatColor.GREEN + " - "  + ChatColor.BOLD + ChatColor.YELLOW + warningLevel; break;
            case 2: warning_text = ChatColor.GREEN + " - "  + ChatColor.BOLD + ChatColor.GOLD + warningLevel; break;
            case 3: warning_text = ChatColor.GREEN + " - "  + ChatColor.BOLD + ChatColor.DARK_RED + warningLevel; break;
            case 4: warning_text = ChatColor.GREEN + " - "  + ChatColor.BOLD + ChatColor.DARK_AQUA + warningLevel; break;
        }

        Score warningLevelDisplay1 = playerObjective.getScore(warning_text);
        Score warningSinceTiksDisplay = playerObjective.getScore(ChatColor.GREEN + "Seconds until alarm");
        Score warningSinceTiksDisplay1 = playerObjective.getScore(ChatColor.GREEN + "level decreases:        ");
        Score warningSinceTiksDisplay2 = playerObjective.getScore(ChatColor.GREEN + " - " + ChatColor.GOLD + secsTillLevelDrop + "      ");
        Score commentDisplay = playerObjective.getScore(getWardenImageText(warningLevel, secsTillLevelDrop)[0]);
        Score commentDisplay1 = playerObjective.getScore(getWardenImageText(warningLevel, secsTillLevelDrop)[1]);
        Score commentDisplay2 = playerObjective.getScore(getWardenImageText(warningLevel, secsTillLevelDrop)[2]);

        if (warningLevel > 0) {
            warningLevelDisplay.setScore(10);
            warningLevelDisplay1.setScore(9);
            warningSinceTiksDisplay.setScore(8);
            warningSinceTiksDisplay1.setScore(7);
            warningSinceTiksDisplay2.setScore(6);
            commentDisplay.setScore(5);
            commentDisplay1.setScore(4);
            commentDisplay2.setScore(3);
        } else if (warningLevel == 0) {
            warningLevelDisplay.setScore(10);
            warningLevelDisplay1.setScore(9);
            commentDisplay.setScore(8);
            commentDisplay1.setScore(7);
            commentDisplay2.setScore(6);
        }
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
        if (prevScoreboard.equals(null)) {
            p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        } else {p.setScoreboard(prevScoreboard);}
        scoreboardEnabled = false;
    }

    private static String[] getWardenImageText(Integer warningLevel, Integer secondsLeft) {
        String msg = "";
        String msg1 = "";
        String msg2 = "";

        if (warningLevel.equals(4)) {
            msg = ChatColor.ITALIC + (ChatColor.BOLD + (ChatColor.DARK_AQUA +"I CAN HEAR YOU..."));
            msg1 = ChatColor.WHITE + "ꨕꨙ\uF801ꨚ";
            msg2 = "";
        } else {
            switch (warningLevel) {
                case 0: {
                    msg = ChatColor.ITALIC + (ChatColor.GREEN +"Well Done");
                    break;}
                case 1: {
                    msg = ChatColor.ITALIC + (ChatColor.YELLOW +"Well... you are safe.");
                    msg1 = ChatColor.ITALIC + (ChatColor.YELLOW +"For now...");
                    break;}
                case 2: {
                    msg = ChatColor.ITALIC + (ChatColor.GOLD +"You were supposed");
                    msg1 = ChatColor.ITALIC + (ChatColor.GOLD +"to be silent...");
                    break;}
                case 3: {
                    msg = ChatColor.ITALIC + (ChatColor.DARK_RED +"I suggest you runnin'");
                    msg1 = ChatColor.ITALIC + (ChatColor.DARK_RED +"outa' there, boi");
                    break;}
            }
            switch (secondsLeft%4) {
                case 3: msg2 = ChatColor.WHITE + "ꨕꨙ\uF801ꨚ";break;
                case 2: msg2 = ChatColor.WHITE + "ꨒꨙ\uF801ꨚ";break;
                case 1: msg2 = ChatColor.WHITE + "ꨓꨛ\uF801ꨜ";break;
                case 0: msg2 = ChatColor.WHITE + "ꨔꨛ\uF801ꨜ";break;
            }
        }
        return new String[] {msg, msg1, msg2};
    }
}
