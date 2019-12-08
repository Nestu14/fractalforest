package com.eclipsekingdom.fractalforest.worldgen.pop;

import com.eclipsekingdom.fractalforest.trees.Species;
import com.eclipsekingdom.fractalforest.util.system.ConsoleSender;
import com.eclipsekingdom.fractalforest.worldgen.pop.util.TreeBiome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.eclipsekingdom.fractalforest.util.language.Message.CONSOLE_FILE_ERROR;

public class PopFlatFile {

    private static final File file = new File("plugins/FractalForest/Data", "populators.yml");
    private static final FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    private static final String header = "Populators";

    public void store(List<TreePopulator> tPops) {
        config.set(header, null);
        for (TreePopulator pop : tPops) {
            String key = header + "." + pop.getName();
            for (Map.Entry<TreeBiome, List<TreeSpawner>> entry : pop.getBiomeToTreeSpawner().entrySet()) {
                TreeBiome biome = entry.getKey();
                List<TreeSpawner> spawners = entry.getValue();
                String biomeKey = key + "." + biome.toString();
                List<String> spawnersList = new ArrayList<>();
                for (TreeSpawner spawner : spawners) {
                    spawnersList.add(spawner.getSpecies().toString() + "=" + spawner.getChance() + "=" + spawner.getMin() + "<" + spawner.getMax() + "=" + spawner.getOverflow());
                }
                config.set(biomeKey, spawnersList);
            }

        }
        save();
    }

    public List<TreePopulator> fetch() {
        List<TreePopulator> tPops = new ArrayList<>();
        if (config.contains(header)) {
            for (String name : config.getConfigurationSection(header).getKeys(false)) {
                String key = header + "." + name;
                LinkedHashMap<TreeBiome, List<TreeSpawner>> biomeToTreeSpawners = new LinkedHashMap<>();
                if (config.contains(key)) {
                    for (String biomeString : config.getConfigurationSection(key).getKeys(false)) {
                        try {
                            TreeBiome biome = TreeBiome.valueOf(biomeString);
                            String biomeKey = key + "." + biomeString;
                            List<TreeSpawner> spawners = new ArrayList<>();
                            if (config.contains(biomeKey)) {
                                List<String> spawnersList = config.getStringList(biomeKey);
                                for (String item : spawnersList) {
                                    try {
                                        String[] parts = item.split("=");
                                        Species species = Species.from(parts[0]);
                                        double chance = Double.parseDouble(parts[1]);
                                        String[] minMaxPart = parts[2].split("<");
                                        int min = Integer.parseInt(minMaxPart[0]), max = Integer.parseInt(minMaxPart[1]);
                                        int overflow = Integer.parseInt(parts[3]);
                                        spawners.add(new TreeSpawner(species, chance, min, max, overflow));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if (spawners.size() > 0) biomeToTreeSpawners.put(biome, spawners);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (biomeToTreeSpawners.size() > 0) tPops.add(new TreePopulator(name, biomeToTreeSpawners));
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
