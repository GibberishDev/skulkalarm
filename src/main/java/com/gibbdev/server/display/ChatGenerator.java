package com.gibbdev.server.display;
import com.gibbdev.SculkAlarm;
import com.gibbdev.server.config.ConfigData;
import com.gibbdev.server.config.ConfigLoader;
import com.jowcey.ExaltedCore.base.dependencies.commons.lang3.StringUtils;
import com.jowcey.ExaltedCore.base.visual.Text;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatGenerator {
    public static void sendAlarmChat(Player p) {
        Integer warningLevel = PlayerDataScraper.getWarningLevel(p);
        Integer alarmSecondsLeft = PlayerDataScraper.getAlarmSecs(p);

        StringBuilder sb = new StringBuilder();
        ConfigData data = SculkAlarm.getPlugin().getConfigLoader().getConfigData();

        sb.append(Text.color(data.getChatLevelLabel().replaceAll("%level%", data.getChatLevelText(warningLevel)))).append("\n")
          .append(Text.color(data.getChatSecondsLabel().replaceAll("%seconds%", alarmSecondsLeft.toString()))).append("\n")
          .append(Text.color(data.getChatComment(warningLevel)));

        p.sendMessage(sb.toString());
    }

    private static final String HOVER_SPLITTER_CHAR = "*";
    private static final String HOVER_SPLITTER_REGEX = "^(\\" + HOVER_SPLITTER_CHAR + "\\" + HOVER_SPLITTER_CHAR + "[A-Za-z0-9{}_\\-+=/'@#~\\[\\]:;()!\"£$%^&*]+\\" + HOVER_SPLITTER_CHAR + "\\" + HOVER_SPLITTER_CHAR + ")";
    public static void sendHoverChat(Player p, String msg) {
        int splitterMarkers = StringUtils.countMatches(msg, HOVER_SPLITTER_CHAR + "" + HOVER_SPLITTER_CHAR);
        if (splitterMarkers % 2 != 0) {
            throw new IllegalArgumentException("Invalid Splitter on '" + msg + "' (Uneven Splitters)");
        }
        HoverEvent messageHover = new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new net.md_5.bungee.api.chat.hover.content.Text(TextComponent.fromLegacyText(Text.MD5Color("Some Hover Text")))
        );

        msg = Text.MD5Color(msg);
        BaseComponent[] components = TextComponent.fromLegacyText(msg);
        List<TextComponent> textComponents = new ArrayList<>();

        Pattern pattern = Pattern.compile(HOVER_SPLITTER_REGEX);
        Matcher matcher = pattern.matcher(msg);

        for (BaseComponent component : components) {
            if (matcher.find()) {
                TextComponent textComponent = new TextComponent(component);
                textComponent.setHoverEvent(messageHover);
                textComponents.add(textComponent);
            } else {
                TextComponent textComponent = new TextComponent(component);
                textComponent.setHoverEvent(null);
                textComponents.add(textComponent);
            }
        }

        p.spigot().sendMessage(textComponents.toArray(new TextComponent[0]));
    }
}