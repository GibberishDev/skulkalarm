package com.gibbdev.server.display;

import com.gibbdev.server.config.ConfigLoader;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

import static com.gibbdev.server.config.ConfigLoader.getColor;

public class ChatGenerator {
    public static void sendAlarmChat(Player p) {
        
        Integer alarmLevel = PlayerDataScraper.getAlarmLevel(p);
        Integer alarmSecondsLeft = PlayerDataScraper.getAlarmSecs(p);
        
        TextColor textColor = TextColor.color(ConfigLoader.textColor.asRGB());
        
        Component txtComp;

        txtComp = Component.text("You have sculk alarm level of ", Style.style(textColor));

        txtComp = txtComp.append(Component.text(alarmLevel, Style.style(getColor(alarmLevel))));

        txtComp = txtComp.append(Component.text(".\nIt will decrease in ", Style.style(textColor)));
        txtComp = txtComp.append(Component.text(alarmSecondsLeft + " ", Style.style(TextColor.color(255, 170 , 0))));
        txtComp = txtComp.append(Component.text("seconds. ", Style.style(textColor)));

        if (ConfigLoader.textChatCommentLabel) {txtComp = getComment(alarmLevel, txtComp);}

        p.sendMessage(txtComp);
    }

    private static Component getComment(Integer alarmLevel, Component msg) {
        switch (alarmLevel) {
            case 0:
                msg = msg.append(Component.text("\nWell done!",
                    Style.style(getColor(alarmLevel), TextDecoration.ITALIC))); break;
            case 1:
                msg = msg.append(Component.text("\nWell... you are safe. For now...",
                    Style.style(getColor(alarmLevel), TextDecoration.ITALIC))); break;
            case 2:
                msg = msg.append(Component.text("\nYou were supposed to be almost silent...",
                    Style.style(getColor(alarmLevel), TextDecoration.ITALIC))); break;
            case 3:
                msg = msg.append(Component.text("\nI suggest you skidadling outa' there, boi",
                    Style.style(getColor(alarmLevel), TextDecoration.ITALIC))); break;
            case 4:
                msg = msg.append(Component.text("\nI CAN HEAR YOU...",
                    Style.style(getColor(alarmLevel), TextDecoration.ITALIC, TextDecoration.BOLD))); break;
        }
        return msg;
    }

}