package com.gibbdev.server.display;

import com.gibbdev.SculkAlarm;
import com.gibbdev.server.config.ConfigLoader;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
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
    static ScoreboardManager mngr = Bukkit.getScoreboardManager();
    static Scoreboard playerBoard = mngr.getNewScoreboard();
    static Objective playerObjective = playerBoard.registerNewObjective("", "");

    static Scoreboard prevScoreboard;
    static Boolean scoreboardEnabled = false;


    static ChatColor txtColor = ChatColor.GREEN;
    static ChatColor lvl0Color = ChatColor.DARK_GREEN;
    static ChatColor lvl1Color = ChatColor.YELLOW;
    static ChatColor lvl2Color = ChatColor.GOLD;
    static ChatColor lvl3Color = ChatColor.DARK_RED;
    static ChatColor lvl4Color = ChatColor.DARK_AQUA;

    private static void updateAlarmSidebar(@NotNull Player p) {
        playerBoard = mngr.getNewScoreboard();
        playerObjective.unregister();
        playerObjective = playerBoard.registerNewObjective("alarmSidebar", "dummy", Component.text("Sculk Alarm", Style.style(TextDecoration.BOLD, TextColor.color(ConfigLoader.sidebarLabelColor.asRGB()))));
        Integer warningLevel = PlayerDataScraper.getAlarmLevel(p);
        Integer secsTillLevelDrop = PlayerDataScraper.getAlarmSecs(p);
        playerObjective.setDisplaySlot(DisplaySlot.SIDEBAR);

        String txtComment = getCommentMessage(warningLevel)[0];
        String txtComment1 = getCommentMessage(warningLevel)[1];

        Score warningLevelDisplay = playerObjective.getScore(txtColor +"Current alarm level:");
        Score warningLevelDisplay1 = playerObjective.getScore(txtColor + " - "  + getColor(warningLevel) + warningLevel);
        Score warningSinceTiksDisplay = playerObjective.getScore(txtColor + "Seconds until alarm");
        Score warningSinceTiksDisplay1 = playerObjective.getScore(txtColor + "level decreases:");
        Score warningSinceTiksDisplay2 = playerObjective.getScore(txtColor + " - " + ChatColor.GOLD + secsTillLevelDrop + "      ");
        Score commentDisplay = playerObjective.getScore(txtComment);
        Score commentDisplay1 = playerObjective.getScore(txtComment1);
        Score wardenDisplay = playerObjective.getScore(getWardenImageText(warningLevel, secsTillLevelDrop));

        List<Score> dispList = new ArrayList<>();
        if (ConfigLoader.textSidebarLevelLabel) {dispList.add(warningLevelDisplay);}
        dispList.add(warningLevelDisplay1);
        if (ConfigLoader.textSidebarSecondsLabel && warningLevel > 0) {dispList.add(warningSinceTiksDisplay); dispList.add(warningSinceTiksDisplay1);}
        if (warningLevel > 0) {dispList.add(warningSinceTiksDisplay2);}
        if (ConfigLoader.textSidebarCommentLabel) {
            if (!(txtComment.equals(""))) {dispList.add(commentDisplay);}
            if (!(txtComment1.equals(""))) {dispList.add(commentDisplay1);}
        }
        if (ConfigLoader.textSidebarImage) {dispList.add(wardenDisplay);}

        for (int i = 0; i < dispList.toArray().length; i++) {
            dispList.get(i).setScore(10 - i);
        }
        dispList.clear();

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
        }.runTaskTimer(SculkAlarm.getPlugin(),0L, 20L);
    }

    public static void clearSidebar(@NotNull Player p) {
        if (prevScoreboard == null) {
            p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        } else {p.setScoreboard(prevScoreboard);}
        scoreboardEnabled = false;
    }

    private static String getWardenImageText(Integer warningLevel, Integer secondsLeft) {
        String msg = "";
        if (warningLevel.equals(4)) {
            msg = ChatColor.WHITE + "ꨕꨙ\uF801ꨚ";
        } else {
            switch (secondsLeft%4) {
                case 3: msg = ChatColor.WHITE + "ꨕꨙ\uF801ꨚ";break;
                case 2: msg = ChatColor.WHITE + "ꨒꨙ\uF801ꨚ";break;
                case 1: msg = ChatColor.WHITE + "ꨓꨛ\uF801ꨜ";break;
                case 0: msg = ChatColor.WHITE + "ꨔꨛ\uF801ꨜ";break;
            }
        }
        return msg;
    }

    private static String[] getCommentMessage(Integer warnLvl) {
        String msg = "";
        String msg1 = "";
        switch (warnLvl) {
            case 0: {
                msg = ChatColor.ITALIC + (getColor(warnLvl) +"Well Done");
                break;}
            case 1: {
                msg = ChatColor.ITALIC + (getColor(warnLvl) +"Well... you are safe.");
                msg1 = ChatColor.ITALIC + (getColor(warnLvl) +"For now...");
                break;}
            case 2: {
                msg = ChatColor.ITALIC + (getColor(warnLvl) +"You were supposed");
                msg1 = ChatColor.ITALIC + (getColor(warnLvl) +"to be silent...");
                break;}
            case 3: {
                msg = ChatColor.ITALIC + (getColor(warnLvl) +"I suggest you runnin'");
                msg1 = ChatColor.ITALIC + (getColor(warnLvl) +"outa' there, boi");
                break;}
            case 4: {
                msg = ChatColor.BOLD + (getColor(warnLvl) + "I CAN HEAR YOU...");
                break;
            }
        }
        return new String[] {msg, msg1};
    }

    private static ChatColor getColor(Integer warnLvl) {
        switch (warnLvl) {
            case 0: return lvl0Color;
            case 1: return lvl1Color;
            case 2: return lvl2Color;
            case 3: return lvl3Color;
            case 4: return lvl4Color;
            default: return txtColor;
        }
    }

    @EventHandler
    public void onPlayerLogOff(PlayerQuitEvent e) {
        clearSidebar(e.getPlayer());
    }


}
