package com.gibbdev.server.config;

import com.gibbdev.SculkAlarm;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class ConfigData {
    @Getter @Setter private String sidebarLabel;
    @Getter @Setter private String sidebarLevelLabel;
    @Getter @Setter private String sidebarLevelPrefix;

    @Getter @Setter private String sidebarLevel0;
    @Getter @Setter private String sidebarLevel1;
    @Getter @Setter private String sidebarLevel2;
    @Getter @Setter private String sidebarLevel3;
    @Getter @Setter private String sidebarLevel4;

    @Getter @Setter private String sidebarSecondsLabelLine1;
    @Getter @Setter private String sidebarSecondsLabelLine2;

    @Getter @Setter private String sidebarSecondsPrefix;

    @Getter @Setter private String sidebarCommentLevel0Line1;
    @Getter @Setter private String sidebarCommentLevel0Line2;

    @Getter @Setter private String sidebarCommentLevel1Line1;
    @Getter @Setter private String sidebarCommentLevel1Line2;

    @Getter @Setter private String sidebarCommentLevel2Line1;
    @Getter @Setter private String sidebarCommentLevel2Line2;

    @Getter @Setter private String sidebarCommentLevel3Line1;
    @Getter @Setter private String sidebarCommentLevel3Line2;

    @Getter @Setter private String sidebarCommentLevel4Line1;
    @Getter @Setter private String sidebarCommentLevel4Line2;

    @Getter @Setter private boolean sidebarImage;

    @Getter @Setter private String chatLevelLabel;
    @Getter @Setter private String chatLevel0;
    @Getter @Setter private String chatLevel1;
    @Getter @Setter private String chatLevel2;
    @Getter @Setter private String chatLevel3;
    @Getter @Setter private String chatLevel4;

    @Getter @Setter private String chatSecondsLabel;
    @Getter @Setter private String chatCommentLevel0;
    @Getter @Setter private String chatCommentLevel1;
    @Getter @Setter private String chatCommentLevel2;
    @Getter @Setter private String chatCommentLevel3;
    @Getter @Setter private String chatCommentLevel4;

    public ConfigData() {
        this.sidebarLabel = "&3&lSculk Alarm";
        this.sidebarLevelLabel = "&aCurrent alarm level:";
        this.sidebarLevelPrefix = "&a - ";

        this.sidebarLevel0 = "&20";
        this.sidebarLevel1 = "&e1";
        this.sidebarLevel2 = "&62";
        this.sidebarLevel3 = "&43";
        this.sidebarLevel4 = "&34";

        this.sidebarSecondsLabelLine1 = "&aSeconds until alarm";
        this.sidebarSecondsLabelLine2 = "&alevel decreases:";

        this.sidebarSecondsPrefix = "&a - &6";

        this.sidebarCommentLevel0Line1 = "&2&oWell done!";
        this.sidebarCommentLevel0Line2 = "";

        this.sidebarCommentLevel1Line1 = "&e&o";
        this.sidebarCommentLevel1Line2 = "&e&o";

        this.sidebarCommentLevel2Line1 = "&6&o";
        this.sidebarCommentLevel2Line2 = "&6&o";

        this.sidebarCommentLevel3Line1 = "&4&o";
        this.sidebarCommentLevel3Line2 = "&4&o";

        this.sidebarCommentLevel4Line1 = "&3&o";
        this.sidebarCommentLevel4Line2 = "";

        this.sidebarImage = true;

        this.chatLevelLabel = "&aYou have sculk alarm level of %level%";

        this.chatLevel0 = "&20&r.";
        this.chatLevel1 = "&e1&r.";
        this.chatLevel2 = "&62&r.";
        this.chatLevel3 = "&43&r.";
        this.chatLevel4 = "&34&r.";

        this.chatSecondsLabel = "&aIt will decrease in &6%seconds% &aseconds";

        this.chatCommentLevel0 = "&2&oWell done!";
        this.chatCommentLevel1 = "&e&oWell... you are safe. For now...";
        this.chatCommentLevel2 = "&6&oYou were supposed to be silent...";
        this.chatCommentLevel3 = "&4&oI suggest you runnin' outa' there, boi";
        this.chatCommentLevel4 = "&3&oI CAN HEAR YOU!!";
    }

    public Map<String, Object> toMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("sidebarLabel", this.sidebarLabel);
        data.put("sidebarLevelLabel", this.sidebarLevelLabel);
        data.put("sidebarLevelPrefix", this.sidebarLevelPrefix);
        data.put("sidebarLevel0", this.sidebarLevel0);
        data.put("sidebarLevel1", this.sidebarLevel1);
        data.put("sidebarLevel2", this.sidebarLevel2);
        data.put("sidebarLevel3", this.sidebarLevel3);
        data.put("sidebarLevel4", this.sidebarLevel4);
        data.put("sidebarSecondsLabelLine1", this.sidebarSecondsLabelLine1);
        data.put("sidebarSecondsLabelLine2", this.sidebarSecondsLabelLine2);
        data.put("sidebarSecondsPrefix", this.sidebarSecondsPrefix);
        data.put("sidebarCommentLevel0Line1", this.sidebarCommentLevel0Line1);
        data.put("sidebarCommentLevel0Line2", this.sidebarCommentLevel0Line2);
        data.put("sidebarCommentLevel1Line1", this.sidebarCommentLevel1Line1);
        data.put("sidebarCommentLevel1Line2", this.sidebarCommentLevel1Line2);
        data.put("sidebarCommentLevel2Line1", this.sidebarCommentLevel2Line1);
        data.put("sidebarCommentLevel2Line2", this.sidebarCommentLevel2Line2);
        data.put("sidebarCommentLevel3Line1", this.sidebarCommentLevel3Line1);
        data.put("sidebarCommentLevel3Line2", this.sidebarCommentLevel3Line2);
        data.put("sidebarCommentLevel4Line1", this.sidebarCommentLevel4Line1);
        data.put("sidebarCommentLevel4Line2", this.sidebarCommentLevel4Line2);
        data.put("sidebarImage", this.sidebarImage);
        data.put("chatLevelLabel", this.chatLevelLabel);
        data.put("chatLevel0", this.chatLevel0);
        data.put("chatLevel1", this.chatLevel1);
        data.put("chatLevel2", this.chatLevel2);
        data.put("chatLevel3", this.chatLevel3);
        data.put("chatLevel4", this.chatLevel4);
        data.put("chatSecondsLabel", this.chatSecondsLabel);
        data.put("chatCommentLevel0", this.chatCommentLevel0);
        data.put("chatCommentLevel1", this.chatCommentLevel1);
        data.put("chatCommentLevel2", this.chatCommentLevel2);
        data.put("chatCommentLevel3", this.chatCommentLevel3);
        data.put("chatCommentLevel4", this.chatCommentLevel4);
        return data;
    }

    public void fromMap(Map<String, Object> data) {
        this.sidebarLabel = (String) data.get("sidebarLabel");
        this.sidebarLevelLabel = (String) data.get("sidebarLevelLabel");
        this.sidebarLevelPrefix = (String) data.get("sidebarLevelPrefix");
        this.sidebarLevel0 = (String) data.get("sidebarLevel0");
        this.sidebarLevel1 = (String) data.get("sidebarLevel1");
        this.sidebarLevel2 = (String) data.get("sidebarLevel2");
        this.sidebarLevel3 = (String) data.get("sidebarLevel3");
        this.sidebarLevel4 = (String) data.get("sidebarLevel4");
        this.sidebarSecondsLabelLine1 = (String) data.get("sidebarSecondsLabelLine1");
        this.sidebarSecondsLabelLine2 = (String) data.get("sidebarSecondsLabelLine2");
        this.sidebarSecondsPrefix = (String) data.get("sidebarSecondsPrefix");
        this.sidebarCommentLevel0Line1 = (String) data.get("sidebarCommentLevel0Line1");
        this.sidebarCommentLevel0Line2 = (String) data.get("sidebarCommentLevel0Line2");
        this.sidebarCommentLevel1Line1 = (String) data.get("sidebarCommentLevel1Line1");
        this.sidebarCommentLevel1Line2 = (String) data.get("sidebarCommentLevel1Line2");
        this.sidebarCommentLevel2Line1 = (String) data.get("sidebarCommentLevel2Line1");
        this.sidebarCommentLevel2Line2 = (String) data.get("sidebarCommentLevel2Line2");
        this.sidebarCommentLevel3Line1 = (String) data.get("sidebarCommentLevel3Line1");
        this.sidebarCommentLevel3Line2 = (String) data.get("sidebarCommentLevel3Line2");
        this.sidebarCommentLevel4Line1 = (String) data.get("sidebarCommentLevel4Line1");
        this.sidebarCommentLevel4Line2 = (String) data.get("sidebarCommentLevel4Line2");
        this.sidebarImage = (boolean) data.get("sidebarImage");
        this.chatLevelLabel = (String) data.get("chatLevelLabel");
        this.chatLevel0 = (String) data.get("chatLevel0");
        this.chatLevel1 = (String) data.get("chatLevel1");
        this.chatLevel2 = (String) data.get("chatLevel2");
        this.chatLevel3 = (String) data.get("chatLevel3");
        this.chatLevel4 = (String) data.get("chatLevel4");
        this.chatSecondsLabel = (String) data.get("chatSecondsLabel");
        this.chatCommentLevel0 = (String) data.get("chatCommentLevel0");
        this.chatCommentLevel1 = (String) data.get("chatCommentLevel1");
        this.chatCommentLevel2 = (String) data.get("chatCommentLevel2");
        this.chatCommentLevel3 = (String) data.get("chatCommentLevel3");
        this.chatCommentLevel4 = (String) data.get("chatCommentLevel4");
    }

    public String getSidebarLevelText(Integer lvl) {
        switch (lvl) {
            default: return sidebarLevel0;
            case 1: return sidebarLevel1;
            case 2: return sidebarLevel2;
            case 3: return sidebarLevel3;
            case 4: return sidebarLevel4;
        }
    }

    public String[] getSidebarSecondsLabel() {
        return new String[] {sidebarSecondsLabelLine1, sidebarSecondsLabelLine2};
    }

    public String getSidebarSecondsPrefix() {
        return sidebarSecondsPrefix;
    }

    public String[] getSidebarComment(Integer lvl) {
        switch (lvl) {
            default: return new String[] {sidebarCommentLevel0Line1, sidebarCommentLevel0Line2};
            case 1: return new String[] {sidebarCommentLevel1Line1, sidebarCommentLevel1Line2};
            case 2: return new String[] {sidebarCommentLevel2Line1, sidebarCommentLevel2Line2};
            case 3: return new String[] {sidebarCommentLevel3Line1, sidebarCommentLevel3Line2};
            case 4: return new String[] {sidebarCommentLevel4Line1, sidebarCommentLevel4Line2};
        }
    }

    public String getChatLevelText(Integer lvl) {
        switch (lvl) {
            default: return chatLevel0;
            case 1: return chatLevel1;
            case 2: return chatLevel2;
            case 3: return chatLevel3;
            case 4: return chatLevel4;
        }
    }

    public String getChatComment(Integer lvl) {
        switch (lvl) {
            default: return chatCommentLevel0;
            case 1: return chatCommentLevel1;
            case 2: return chatCommentLevel2;
            case 3: return chatCommentLevel3;
            case 4: return chatCommentLevel4;
        }
    }
}
