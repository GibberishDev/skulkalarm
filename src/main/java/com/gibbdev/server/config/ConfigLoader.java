package com.gibbdev.server.config;

import com.gibbdev.SculkAlarm;
import lombok.Getter;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

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

        Yaml yaml = new Yaml(new CustomClassLoaderConstructor(ConfigData.class.getClassLoader()));
        try (InputStream in = new FileInputStream(this.plugin.getConfigName())) {
            this.configData = yaml.loadAs(in, ConfigData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
