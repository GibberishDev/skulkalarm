package com.gibbdev.server.config;

import com.gibbdev.SculkAlarm;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;

public class ConfigLoader{
    static Plugin  plugin = SculkAlarm.getPlugin();

    public static String sidebarLabel = "";
    public static String sidebarLevelLabel = "";
    public static String sidebarLevelPrefix = "";
    public static String sidebarLevel0 = "";
    public static String sidebarLevel1 = "";
    public static String sidebarLevel2 = "";
    public static String sidebarLevel3 = "";
    public static String sidebarLevel4 = "";
    public static String sidebarSecondsLabelLine1 = "";
    public static String sidebarSecondsLabelLine2 = "";
    public static String sidebarSecondsPrefix = "";
    public static String sidebarCommentLevel0Line1 = "";
    public static String sidebarCommentLevel0Line2 = "";
    public static String sidebarCommentLevel1Line1 = "";
    public static String sidebarCommentLevel1Line2 = "";
    public static String sidebarCommentLevel2Line1 = "";
    public static String sidebarCommentLevel2Line2 = "";
    public static String sidebarCommentLevel3Line1 = "";
    public static String sidebarCommentLevel3Line2 = "";
    public static String sidebarCommentLevel4Line1 = "";
    public static String sidebarCommentLevel4Line2 = "";
    public static boolean sidebarImage = true;

    public static String chatLevelLabel = "";
    public static String chatLevel0 = "";
    public static String chatLevel1 = "";
    public static String chatLevel2 = "";
    public static String chatLevel3 = "";
    public static String chatLevel4 = "";
    public static String chatSecondsLabel = "";
    public static String chatCommentLevel0 = "";
    public static String chatCommentLevel1 = "";
    public static String chatCommentLevel2 = "";
    public static String chatCommentLevel3 = "";
    public static String chatCommentLevel4 = "";
    
    public static void reloadConfig() {
        Configuration config = plugin.getConfig();
        sidebarLabel = (String) config.get("sidebar-label");
        
        sidebarLevelLabel = (String) config.get("sidebar-level-label");

        sidebarLevelPrefix = (String) config.get("sidebar-level-prefix");
        
        sidebarLevel0 = (String) config.get("sidebar-level-0");
        sidebarLevel1 = (String) config.get("sidebar-level-1");
        sidebarLevel2 = (String) config.get("sidebar-level-2");
        sidebarLevel3 = (String) config.get("sidebar-level-3");
        sidebarLevel4 = (String) config.get("sidebar-level-4");
        
        sidebarSecondsLabelLine1 = (String) config.get("sidebar-seconds-label-line-1");
        sidebarSecondsLabelLine2 = (String) config.get("sidebar-seconds-label-line-2");

        sidebarSecondsPrefix = (String) config.get("sidebar-seconds-prefix");
        
        sidebarCommentLevel0Line1 = (String) config.get("sidebar-comment-level-0-line-1");
        sidebarCommentLevel0Line2 = (String) config.get("sidebar-comment-level-0-line-2");
        sidebarCommentLevel1Line1 = (String) config.get("sidebar-comment-level-1-line-1");
        sidebarCommentLevel1Line2 = (String) config.get("sidebar-comment-level-1-line-2");
        sidebarCommentLevel2Line1 = (String) config.get("sidebar-comment-level-2-line-1");
        sidebarCommentLevel2Line2 = (String) config.get("sidebar-comment-level-2-line-2");
        sidebarCommentLevel3Line1 = (String) config.get("sidebar-comment-level-3-line-1");
        sidebarCommentLevel3Line2 = (String) config.get("sidebar-comment-level-3-line-2");
        sidebarCommentLevel4Line1 = (String) config.get("sidebar-comment-level-4-line-1");
        sidebarCommentLevel4Line2 = (String) config.get("sidebar-comment-level-4-line-2");

        sidebarImage = config.get("sidebar-image").equals(1);
        
        chatLevelLabel = (String) config.get("chat-level-label");
        
        chatLevel0 = (String) config.get("chat-level-0");
        chatLevel1 = (String) config.get("chat-level-1");
        chatLevel2 = (String) config.get("chat-level-2");
        chatLevel3 = (String) config.get("chat-level-3");
        chatLevel4 = (String) config.get("chat-level-4");
        
        chatSecondsLabel = (String) config.get("chat-seconds-label");
        
        chatCommentLevel0 = (String) config.get("chat-comment-level-0-label");
        chatCommentLevel1 = (String) config.get("chat-comment-level-1-label");
        chatCommentLevel2 = (String) config.get("chat-comment-level-2-label");
        chatCommentLevel3 = (String) config.get("chat-comment-level-3-label");
        chatCommentLevel4 = (String) config.get("chat-comment-level-4-label");
    }

    public static String getSidebarLabel() {
        return sidebarLabel;
    }

    public static String getSidebarLevelLabel() {
        return sidebarLevelLabel;
    }

    public static String getSidebarLevelPrefix() {
        return sidebarLevelPrefix;
    }

    public static String getSidebarLevelText(Integer lvl) {
        switch (lvl) {
            default: return sidebarLevel0;
            case 1: return sidebarLevel1;
            case 2: return sidebarLevel2;
            case 3: return sidebarLevel3;
            case 4: return sidebarLevel4;
        }
    }

    public static String[] getSidebarSecondsLabel() {
        return new String[] {sidebarSecondsLabelLine1, sidebarSecondsLabelLine2};
    }

    public static String getSidebarSecondsPrefix() {
        return sidebarSecondsPrefix;
    }

    public static String[] getSidebarComment(Integer lvl) {
        switch (lvl) {
            default: return new String[] {sidebarCommentLevel0Line1, sidebarCommentLevel0Line2};
            case 1: return new String[] {sidebarCommentLevel1Line1, sidebarCommentLevel1Line2};
            case 2: return new String[] {sidebarCommentLevel2Line1, sidebarCommentLevel2Line2};
            case 3: return new String[] {sidebarCommentLevel3Line1, sidebarCommentLevel3Line2};
            case 4: return new String[] {sidebarCommentLevel4Line1, sidebarCommentLevel4Line2};
        }
    }

    public static boolean getImageState() {
        return sidebarImage;
    }

    public static String getChatLevelLabel() {
        return chatLevelLabel;
    }

    public static String getChatLevelText(Integer lvl) {
        switch (lvl) {
            default: return chatLevel0;
            case 1: return chatLevel1;
            case 2: return chatLevel2;
            case 3: return chatLevel3;
            case 4: return chatLevel4;
        }
    }

    public static String getChatSecondsLabel() {
        return chatSecondsLabel;
    }

    public static String getChatComment(Integer lvl) {
        switch (lvl) {
            default: return chatCommentLevel0;
            case 1: return chatCommentLevel1;
            case 2: return chatCommentLevel2;
            case 3: return chatCommentLevel3;
            case 4: return chatCommentLevel4;
        }
    }
}
