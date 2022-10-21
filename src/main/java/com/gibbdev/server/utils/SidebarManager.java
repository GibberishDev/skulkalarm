package com.gibbdev.server.utils;

import com.gibbdev.SculkAlarm;
import com.gibbdev.server.display.PlayerDataScraper;
import com.jowcey.ExaltedCore.base.visual.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SidebarManager {
    static ScoreboardManager mngr = Bukkit.getScoreboardManager();
    static Scoreboard playerBoard = mngr.getNewScoreboard();
    static Objective playerObjective = playerBoard.registerNewObjective("", "");

    static Scoreboard prevScoreboard;
    static Boolean scoreboardEnabled = false;

    private SculkAlarm plugin;

    public SidebarManager(SculkAlarm plugin) {
        this.plugin = plugin;
    }

    private void updateAlarmSidebar(@NotNull Player p) {
        playerBoard = mngr.getNewScoreboard();
        playerObjective.unregister();
        playerObjective = playerBoard.registerNewObjective(Text.color(this.plugin.getConfigLoader().getConfigData().getSidebarLabel()), "dummy");
        Integer warningLevel = PlayerDataScraper.getWarningLevel(p);
        Integer alarmSecondsLeft = PlayerDataScraper.getAlarmSecs(p);
        playerObjective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score warningLevelDisplay =     playerObjective.getScore(Text.color(this.plugin.getConfigLoader().getConfigData().getSidebarLevelLabel()));
        Score warningLevelDisplay1 =    playerObjective.getScore(Text.color(this.plugin.getConfigLoader().getConfigData().getSidebarLevelPrefix() + this.plugin.getConfigLoader().getConfigData().getSidebarLevelText(warningLevel)));
        Score warningSecondsDisplay =   playerObjective.getScore(Text.color(this.plugin.getConfigLoader().getConfigData().getSidebarSecondsLabel()[0]));
        Score warningSecondsDisplay1 =  playerObjective.getScore(Text.color(this.plugin.getConfigLoader().getConfigData().getSidebarSecondsLabel()[1]));
        Score warningSecondsDisplay2 =  playerObjective.getScore(Text.color(this.plugin.getConfigLoader().getConfigData().getSidebarSecondsPrefix() + alarmSecondsLeft));
        Score commentDisplay =          playerObjective.getScore(Text.color(this.plugin.getConfigLoader().getConfigData().getSidebarComment(warningLevel)[0]));
        Score commentDisplay1 =         playerObjective.getScore(Text.color(this.plugin.getConfigLoader().getConfigData().getSidebarComment(warningLevel)[1]));
        Score wardenDisplay =           playerObjective.getScore(Text.color(getWardenImageText(warningLevel, alarmSecondsLeft)));

        List<Score> dispList = new ArrayList<>();
        if (!(this.plugin.getConfigLoader().getConfigData().getSidebarLevelLabel().equals(""))) {dispList.add(warningLevelDisplay);}
        dispList.add(warningLevelDisplay1);
        if (!(this.plugin.getConfigLoader().getConfigData().getSidebarSecondsLabel()[0].equals("")) && warningLevel > 0) {dispList.add(warningSecondsDisplay);}
        if (!(this.plugin.getConfigLoader().getConfigData().getSidebarSecondsLabel()[1].equals("")) && warningLevel > 0) {dispList.add(warningSecondsDisplay1);}
        if (warningLevel > 0) {dispList.add(warningSecondsDisplay2);}
        if (!(this.plugin.getConfigLoader().getConfigData().getSidebarComment(warningLevel)[0].equals(""))) {dispList.add(commentDisplay);}
        if (!(this.plugin.getConfigLoader().getConfigData().getSidebarComment(warningLevel)[1].equals(""))) {dispList.add(commentDisplay1);}
        if (this.plugin.getConfigLoader().getConfigData().isSidebarImage()) {dispList.add(wardenDisplay);}

        for (int i = 0; i < dispList.toArray().length; i++) {
            dispList.get(i).setScore(10 - i);
        }
        dispList.clear();

        p.setScoreboard(playerBoard);
    }

    public void toggleSidebar(@NotNull Player p) {
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

    public void clearSidebar(@NotNull Player p) {
        if (prevScoreboard == null) {
            p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        } else {p.setScoreboard(prevScoreboard);}
        scoreboardEnabled = false;
    }

    private String getWardenImageText(Integer warningLevel, Integer secondsLeft) {
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
}
