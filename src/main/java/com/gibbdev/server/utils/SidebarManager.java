package com.gibbdev.server.utils;

import com.gibbdev.SculkAlarm;
import com.gibbdev.server.display.PlayerDataScraper;
import com.jowcey.ExaltedCore.ExaltedCore;
import com.jowcey.ExaltedCore.base.dependencies.commons.lang3.StringUtils;
import com.jowcey.ExaltedCore.base.scheduler.RecurringTask;
import com.jowcey.ExaltedCore.base.visual.Text;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.logging.Level;

public class SidebarManager {
    private final SculkAlarm plugin;

    private ScoreboardManager mngr;

    @Getter
    private RecurringTask task;
    @Getter
    private final Map<UUID, Scoreboard> oldScoreBoards = new HashMap<>();
    @Getter
    private final Map<UUID, Scoreboard> enabledPlayers = new HashMap<>();

    public SidebarManager(SculkAlarm plugin) {
        this.plugin = plugin;
        this.mngr = Bukkit.getScoreboardManager();
        this.task = ExaltedCore.get().getScheduler().runTaskTimer(() -> {
            for (UUID uuid : enabledPlayers.keySet()) {
                Player player = Bukkit.getPlayer(uuid);
                if (player != null) {
                    updateAlarmSidebar(player);
                }
            }
        }, 0L, 1L);
        this.task.start();
    }

    private void initAlarmSidebar(@NotNull Player p) {
        if (!this.enabledPlayers.containsKey(p.getUniqueId()))
            return;
        if (this.enabledPlayers.get(p.getUniqueId()) == null || this.enabledPlayers.get(p.getUniqueId()).getObjective(p.getUniqueId().toString()) == null) {
            Scoreboard sb = mngr.getNewScoreboard();
            Objective o = sb.registerNewObjective(p.getUniqueId().toString(), Criteria.DUMMY, Component.empty());
            o.setDisplaySlot(DisplaySlot.SIDEBAR);
            this.enabledPlayers.put(p.getUniqueId(), sb);
        }
        Scoreboard board = this.enabledPlayers.get(p.getUniqueId());
        Objective objective = this.enabledPlayers.get(p.getUniqueId()).getObjective(p.getUniqueId().toString());
        assert objective != null;
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        int lines = 10;
        for (int i = 0; i < lines; i++) {
            Team team = board.registerNewTeam("" + i);
            team.addEntry(ChatColor.values()[i].toString());
            objective.getScore(ChatColor.values()[i].toString()).setScore(lines - i - 1);
        }

        if (!p.getScoreboard().equals(this.enabledPlayers.get(p.getUniqueId()))) {
            p.setScoreboard(this.enabledPlayers.get(p.getUniqueId()));
        }
    }

    private void updateAlarmSidebar(@NotNull Player p) {
        if (!this.enabledPlayers.containsKey(p.getUniqueId()))
            return;
        if (this.enabledPlayers.get(p.getUniqueId()) == null || this.enabledPlayers.get(p.getUniqueId()).getObjective(p.getUniqueId().toString()) == null) {
            initAlarmSidebar(p);
        }

        Integer warningLevel = PlayerDataScraper.getWarningLevel(p);
        Integer alarmSecondsLeft = PlayerDataScraper.getAlarmSecs(p);

        setLine(p, 0, this.plugin.getConfigLoader().getConfigData().getSidebarLevelLabel());
        setLine(p, 1, this.plugin.getConfigLoader().getConfigData().getSidebarLevelPrefix() + this.plugin.getConfigLoader().getConfigData().getSidebarLevelText(warningLevel));
        setLine(p, 2, this.plugin.getConfigLoader().getConfigData().getSidebarSecondsLabel()[0]);
        setLine(p, 3, this.plugin.getConfigLoader().getConfigData().getSidebarSecondsLabel()[1]);
        setLine(p, 4, this.plugin.getConfigLoader().getConfigData().getSidebarSecondsPrefix() + alarmSecondsLeft);
        setLine(p, 5, this.plugin.getConfigLoader().getConfigData().getSidebarComment(warningLevel)[0]);
        setLine(p, 6, this.plugin.getConfigLoader().getConfigData().getSidebarComment(warningLevel)[1]);
        setLine(p, 7, getWardenImageText(warningLevel, alarmSecondsLeft));

    }

    private void setLine(@NotNull Player p, int i, String text) {
        Scoreboard board = this.enabledPlayers.get(p.getUniqueId());
        Team team = board.getTeam("" + i);
        assert team != null;
        String t = Text.MD5Color(text);
        team.prefix(Component.text(t));
    }

    public void toggleSidebar(@NotNull Player p) {
        if (enabledPlayers.containsKey(p.getUniqueId())) {
            this.clearSidebar(p);
        } else {
            oldScoreBoards.put(p.getUniqueId(), p.getScoreboard());
            enabledPlayers.put(p.getUniqueId(), null);
        }
    }

    public void clearSidebar(@NotNull Player p) {
        if (oldScoreBoards.get(p.getUniqueId()) == null) {
            p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        } else {
            p.setScoreboard(oldScoreBoards.get(p.getUniqueId()));
        }
        oldScoreBoards.remove(p.getUniqueId());
        enabledPlayers.remove(p.getUniqueId());
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
