package com.eclipsekingdom.fractalforest.encyclopedia;

import com.eclipsekingdom.fractalforest.trees.Species;
import com.eclipsekingdom.fractalforest.sys.ConsoleSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.eclipsekingdom.fractalforest.sys.language.Message.CONSOLE_FILE_ERROR;

public class EncyclopediaFlatFile {

    private static final File file = new File("plugins/FractalForest/Data", "encyclopedia.yml");
    private static final FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    private static final String header = "EncyclopediaCache";

    public void store(Map<String, Entry> worldToData) {
        config.set(header, null);
        for (Map.Entry<String, Entry> entry : worldToData.entrySet()) {
            String key = header + "." + entry.getKey();
            Entry entryLog = entry.getValue();
            config.set(key + ".observed", entryLog.getSpecimensObserved());
            config.set(key + ".avgHeight", entryLog.getAverageHeight());
            config.set(key + ".avgSpread", entryLog.getAverageSpread());
            config.set(key + ".avgVolume", entryLog.getAverageVolume());
        }
        save();
    }

    public Map<String, Entry> fetch() {
        Map<String, Entry> speciesToEntry = new HashMap<>();
        if (config.contains(header)) {
            for (String species : config.getConfigurationSection(header).getKeys(false)) {
                if (exists(species)) {
                    try {
                        String key = header + "." + species;
                        int observed = config.getInt(key + ".observed");
                        double h = config.getDouble(key + ".avgHeight");
                        double s = config.getDouble(key + ".avgSpread");
                        double v = config.getDouble(key + ".avgVolume");

                        speciesToEntry.put(species, new Entry(observed, h, s, v));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return speciesToEntry;
    }

    private boolean exists(String species) {
        for (Species oneSpecies : Species.values()) {
            if (oneSpecies.toString().equals(species)) {
                return true;
            }
        }
        return false;
    }

    private void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            ConsoleSender.sendMessage(CONSOLE_FILE_ERROR.fromFile(file.getName()));
        }
    }

}
