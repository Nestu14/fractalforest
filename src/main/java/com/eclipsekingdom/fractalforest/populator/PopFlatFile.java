package com.eclipsekingdom.fractalforest.populator;

import com.eclipsekingdom.fractalforest.util.ConsoleSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.eclipsekingdom.fractalforest.util.language.Message.CONSOLE_FILE_ERROR;
import static com.eclipsekingdom.fractalforest.util.language.Message.CONSOLE_TPOP_ERROR;

public class PopFlatFile {

    private static final File file = new File("plugins/FractalForest/data", "populators.yml");
    private static final FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    private static final String header = "Populators";

    public void store(List<TreePopulator> tPops) {
        config.set(header, null);
        for (TreePopulator pop : tPops) {
            String key = header + "." + pop.getName();
        }
        save();
    }

    public List<TreePopulator> fetch() {
        List<TreePopulator> tPops = new ArrayList<>();
        if (config.contains(header)) {
            for (String name : config.getConfigurationSection(header).getKeys(false)) {
                try {
                    String key = header + "." + name;
                } catch (Exception e) {
                    ConsoleSender.sendMessage(CONSOLE_TPOP_ERROR.getFromPop(name));
                }
            }
        }
        return tPops;
    }


    private void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            ConsoleSender.sendMessage(CONSOLE_FILE_ERROR.getFromFile(file.getName()));
        }
    }

}
