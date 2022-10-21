package com.gibbdev.server.config;

import com.gibbdev.SculkAlarm;
import lombok.Getter;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;

public class ConfigLoader{
    private final SculkAlarm plugin;
    @Getter
    private ConfigData configData;

    public ConfigLoader(SculkAlarm plugin) {
        this.plugin = plugin;
        this.configData = new ConfigData();
        this.load();
    }

    public void load() {
        if (!(new File(this.plugin.getConfigName()).exists())) {
            this.save();
            return;
        }

        Yaml yaml = new Yaml(new Constructor(ConfigData.class));
        try (InputStream in = new FileInputStream(this.plugin.getConfigName())) {
            this.configData = yaml.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Configuration config = plugin.getConfig();
//
//        this.configData.setSidebarLabel(config.getString("sidebar-label"));
//
//        this.configData.setSidebarLevelLabel(config.getString("sidebar-level-label"));
//
//        this.configData.setSidebarLevelPrefix(config.getString("sidebar-level-prefix"));
//
//        this.configData.setSidebarLevel0(config.getString("sidebar-level-0"));
//        this.configData.setSidebarLevel1(config.getString("sidebar-level-1"));
//        this.configData.setSidebarLevel2(config.getString("sidebar-level-2"));
//        this.configData.setSidebarLevel3(config.getString("sidebar-level-3"));
//        this.configData.setSidebarLevel4(config.getString("sidebar-level-4"));
//
//        this.configData.setSidebarSecondsLabelLine1(config.getString("sidebar-seconds-label-line-1"));
//        this.configData.setSidebarSecondsLabelLine2(config.getString("sidebar-seconds-label-line-2"));
//
//        this.configData.setSidebarSecondsPrefix(config.getString("sidebar-seconds-prefix"));
//
//        this.configData.setSidebarCommentLevel0Line1(config.getString("sidebar-comment-level-0-line-1"));
//        this.configData.setSidebarCommentLevel0Line2(config.getString("sidebar-comment-level-0-line-2"));
//        this.configData.setSidebarCommentLevel1Line1(config.getString("sidebar-comment-level-1-line-1"));
//        this.configData.setSidebarCommentLevel1Line2(config.getString("sidebar-comment-level-1-line-2"));
//        this.configData.setSidebarCommentLevel2Line1(config.getString("sidebar-comment-level-2-line-1"));
//        this.configData.setSidebarCommentLevel2Line2(config.getString("sidebar-comment-level-2-line-2"));
//        this.configData.setSidebarCommentLevel3Line1(config.getString("sidebar-comment-level-3-line-1"));
//        this.configData.setSidebarCommentLevel3Line2(config.getString("sidebar-comment-level-3-line-2"));
//        this.configData.setSidebarCommentLevel4Line1(config.getString("sidebar-comment-level-4-line-1"));
//        this.configData.setSidebarCommentLevel4Line2(config.getString("sidebar-comment-level-4-line-2"));
//
//        this.configData.setSidebarImage(config.getBoolean("sidebar-image"));
//
//        this.configData.setChatLevelLabel(config.getString("chat-level-label"));
//
//        this.configData.setChatLevel0(config.getString("chat-level-0"));
//        this.configData.setChatLevel1(config.getString("chat-level-1"));
//        this.configData.setChatLevel2(config.getString("chat-level-2"));
//        this.configData.setChatLevel3(config.getString("chat-level-3"));
//        this.configData.setChatLevel4(config.getString("chat-level-4"));
//
//        this.configData.setChatSecondsLabel(config.getString("chat-seconds-label"));
//
//        this.configData.setChatCommentLevel0(config.getString("chat-comment-level-0-label"));
//        this.configData.setChatCommentLevel1(config.getString("chat-comment-level-1-label"));
//        this.configData.setChatCommentLevel2(config.getString("chat-comment-level-2-label"));
//        this.configData.setChatCommentLevel3(config.getString("chat-comment-level-3-label"));
//        this.configData.setChatCommentLevel4(config.getString("chat-comment-level-4-label"));
    }

    public void save() {
        try {
            File file = new File(this.plugin.getConfigName());
            File dir = new File(this.plugin.getDataFolder() + "/");
            if (!dir.exists())
                dir.mkdirs();
            if (!file.exists())
                file.createNewFile();
            PrintWriter writer = new PrintWriter(file);
            DumperOptions options = new DumperOptions();
            options.setIndent(2);
            options.setPrettyFlow(true);
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);
            yaml.dump(this.configData, writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
