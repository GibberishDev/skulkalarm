package com.gibbdev.server.display;
import com.gibbdev.server.config.ConfigLoader;
import org.bukkit.entity.Player;

public class ChatGenerator {
    public static void sendAlarmChat(Player p) {
        
        Integer warningLevel = PlayerDataScraper.getWarningLevel(p);
        Integer alarmSecondsLeft = PlayerDataScraper.getAlarmSecs(p);

        String message =
                ConfigLoader.getChatLevelLabel().replaceAll("%level%", ConfigLoader.getChatLevelText(warningLevel)) +
                ConfigLoader.getChatSecondsLabel().replaceAll("%seconds%", alarmSecondsLeft.toString()) +
                ConfigLoader.getChatComment(warningLevel);

        p.sendMessage(message);
    }

}