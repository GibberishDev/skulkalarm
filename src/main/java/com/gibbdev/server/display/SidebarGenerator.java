package com.gibbdev.server.display;

import com.gibbdev.SculkAlarm;
import com.gibbdev.server.config.ConfigLoader;
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

    private static void updateAlarmSidebar(@NotNull Player p) {
        playerBoard = mngr.getNewScoreboard();
        playerObjective.unregister();
        playerObjective = playerBoard.registerNewObjective(ConfigLoader.getSidebarLabel(), "dummy");
        Integer warningLevel = PlayerDataScraper.getWarningLevel(p);
        Integer alarmSecondsLeft = PlayerDataScraper.getAlarmSecs(p);
        playerObjective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score warningLevelDisplay =     playerObjective.getScore(ConfigLoader.getSidebarLevelLabel());
        Score warningLevelDisplay1 =    playerObjective.getScore(ConfigLoader.getSidebarLevelPrefix() + ConfigLoader.getSidebarLevelText(warningLevel));
        Score warningSecondsDisplay =   playerObjective.getScore(ConfigLoader.getSidebarSecondsLabel()[0]);
        Score warningSecondsDisplay1 =  playerObjective.getScore(ConfigLoader.getSidebarSecondsLabel()[1]);
        Score warningSecondsDisplay2 =  playerObjective.getScore(ConfigLoader.getSidebarSecondsPrefix() + alarmSecondsLeft);
        Score commentDisplay =          playerObjective.getScore(ConfigLoader.getSidebarComment(warningLevel)[0]);
        Score commentDisplay1 =         playerObjective.getScore(ConfigLoader.getSidebarComment(warningLevel)[1]);
        Score wardenDisplay =           playerObjective.getScore(getWardenImageText(warningLevel, alarmSecondsLeft));

        List<Score> dispList = new ArrayList<>();
        if (!(ConfigLoader.getSidebarLevelLabel().equals(""))) {dispList.add(warningLevelDisplay);}
        dispList.add(warningLevelDisplay1);
        if (!(ConfigLoader.getSidebarSecondsLabel()[0].equals("")) && warningLevel > 0) {dispList.add(warningSecondsDisplay);}
        if (!(ConfigLoader.getSidebarSecondsLabel()[1].equals("")) && warningLevel > 0) {dispList.add(warningSecondsDisplay1);}
        if (warningLevel > 0) {dispList.add(warningSecondsDisplay2);}
        if (!(ConfigLoader.getSidebarComment(warningLevel)[0].equals(""))) {dispList.add(commentDisplay);}
        if (!(ConfigLoader.getSidebarComment(warningLevel)[1].equals(""))) {dispList.add(commentDisplay1);}
        if (ConfigLoader.getImageState()) {dispList.add(wardenDisplay);}

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

    @EventHandler
    public void onPlayerLogOff(PlayerQuitEvent e) {
        clearSidebar(e.getPlayer());
    }

}
