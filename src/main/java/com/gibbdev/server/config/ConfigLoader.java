package com.gibbdev.server.config;

import com.gibbdev.SculkAlram;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;

public class ConfigLoader{
    static Plugin  plugin = SculkAlram.getPlugin();

    public static Color textColor = Color.fromRGB(120, 255, 120);
    public static Color sidebarLabelColor = Color.fromRGB(250, 120, 0);
    public static Color lvl0Color = Color.fromRGB(60, 255, 60);
    public static Color lvl1Color = Color.fromRGB(220, 200, 0);
    public static Color lvl2Color = Color.fromRGB(250, 120, 0);
    public static Color lvl3Color = Color.fromRGB(255, 40, 0);
    public static Color lvl4Color = Color.fromRGB(10, 70, 80);

    public static Boolean textSidebarLevelLabel = true;
    public static Boolean textSidebarSecondsLabel = true;
    public static Boolean textSidebarCommentLabel = true;
    public static Boolean textSidebarImage = true;
    public static Boolean textChatCommentLabel = true;

    public static void loadConfig() {
        Configuration config = plugin.getConfig();
        textColor = Color.fromRGB((Integer) config.get("colors.text.r"),(Integer) config.get("colors.text.g") ,(Integer) config.get("colors.text.b"));
        sidebarLabelColor = Color.fromRGB((Integer) config.get("colors.sidebar-label.r"),(Integer) config.get("colors.sidebar-label.g") ,(Integer) config.get("colors.sidebar-label.b"));
        lvl0Color = Color.fromRGB((Integer) config.get("colors.warning-level-0.r"),(Integer) config.get("colors.warning-level-0.g") ,(Integer) config.get("colors.warning-level-0.b"));
        lvl1Color = Color.fromRGB((Integer) config.get("colors.warning-level-1.r"),(Integer) config.get("colors.warning-level-1.g") ,(Integer) config.get("colors.warning-level-1.b"));
        lvl2Color = Color.fromRGB((Integer) config.get("colors.warning-level-2.r"),(Integer) config.get("colors.warning-level-2.g") ,(Integer) config.get("colors.warning-level-2.b"));
        lvl3Color = Color.fromRGB((Integer) config.get("colors.warning-level-3.r"),(Integer) config.get("colors.warning-level-3.g") ,(Integer) config.get("colors.warning-level-3.b"));
        lvl4Color = Color.fromRGB((Integer) config.get("colors.warning-level-4.r"),(Integer) config.get("colors.warning-level-4.g") ,(Integer) config.get("colors.warning-level-4.b"));

        textSidebarLevelLabel = config.get("texts.sidebar-level-label.enabled").equals(1);
        textSidebarSecondsLabel = config.get("texts.sidebar-seconds-label.enabled").equals(1);
        textSidebarCommentLabel = config.get("texts.sidebar-comment-label.enabled").equals(1);
        textSidebarImage = config.get("texts.sidebar-warden-image.enabled").equals(1);
        textChatCommentLabel = config.get("texts.chat-comment-label.enabled").equals(1);
    }

    public static TextColor getColor(Integer lvl) {
        switch (lvl) {
            case 0:
                return TextColor.color(lvl0Color.asRGB());
            case 1:
                return TextColor.color(lvl1Color.asRGB());
            case 2:
                return TextColor.color(lvl2Color.asRGB());
            case 3:
                return TextColor.color(lvl3Color.asRGB());
            case 4:
                return TextColor.color(lvl4Color.asRGB());
            default:
                return TextColor.color(textColor.asRGB());
        }
    }

}
