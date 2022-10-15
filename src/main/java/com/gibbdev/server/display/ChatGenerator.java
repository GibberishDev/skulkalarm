package com.gibbdev.server.display;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class ChatGenerator {
    public static void sendAlarmChat(Player p) {
        Integer alarmLevel = PlayerDataScraper.getAlarmLevel(p);
        Integer alarmSecondsLeft = PlayerDataScraper.getAlarmSecs(p);
        Component txtComp;
        txtComp = Component.text("Your have sculk alarm level of ", Style.style(TextColor.color(120, 255, 120)));
        switch (alarmLevel) {
            case 0: txtComp = txtComp.append(
                Component.text("0. ", Style.style(TextColor.color(60, 255 , 60), TextDecoration.BOLD))).append(
                Component.text("Well Done!", Style.style(TextColor.color(120, 255, 120), TextDecoration.ITALIC)));
                    break;
            case 1: txtComp = txtComp.append(
                Component.text(alarmLevel + " ", Style.style(TextColor.color(220, 200 , 0), TextDecoration.BOLD))).append(
                Component.text("and it will decrease in ", Style.style(TextColor.color(120, 255 , 120)))).append(
                Component.text(alarmSecondsLeft + " ", Style.style(TextColor.color(255, 170 , 0)))).append(
                Component.text("seconds. ", Style.style(TextColor.color(120, 255 , 120)))).append(
                Component.text("Well... you are in the clear. For now...", Style.style(TextColor.color(220, 200 , 0), TextDecoration.ITALIC)));
                    break;
            case 2: txtComp = txtComp.append(
                Component.text(alarmLevel + " ", Style.style(TextColor.color(255, 120, 0), TextDecoration.BOLD))).append(
                Component.text("and it will decrease in ", Style.style(TextColor.color(120, 255 , 120)))).append(
                Component.text(alarmSecondsLeft + " ", Style.style(TextColor.color(255, 170 , 0)))).append(
                Component.text("seconds. ", Style.style(TextColor.color(120, 255 , 120)))).append(
                Component.text("You were supposed to be almost silent...", Style.style(TextColor.color(255, 120, 0), TextDecoration.ITALIC)));
                break;
            case 3: txtComp = txtComp.append(
                Component.text(alarmLevel + " ", Style.style(TextColor.color(255, 40, 0), TextDecoration.BOLD))).append(
                Component.text("and it will decrease in ", Style.style(TextColor.color(120, 255 , 120)))).append(
                Component.text(alarmSecondsLeft + " ", Style.style(TextColor.color(255, 170 , 0)))).append(
                Component.text("seconds. ", Style.style(TextColor.color(120, 255 , 120)))).append(
                Component.text("I suggest you skidadling outa' there, boi", Style.style(TextColor.color(255, 40, 0), TextDecoration.ITALIC)));
                break;
            case 4: txtComp = txtComp.append(
                Component.text(alarmLevel + " ", Style.style(TextColor.color(10, 70, 80), TextDecoration.BOLD))).append(
                Component.text("and it will decrease in ", Style.style(TextColor.color(120, 255 , 120)))).append(
                Component.text(alarmSecondsLeft + " ", Style.style(TextColor.color(255, 170, 0)))).append(
                Component.text("seconds. ", Style.style(TextColor.color(120, 255 , 120)))).append(
                Component.text("I CAN HEAR YOU...", Style.style(TextColor.color(10, 70, 80), TextDecoration.ITALIC, TextDecoration.BOLD)));
                break;
        }
        p.sendMessage(txtComp);
    }
}
//TODO: add custom characters support