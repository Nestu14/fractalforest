package com.eclipsekingdom.fractalforest.gen.pop;

import com.eclipsekingdom.fractalforest.gen.pop.util.TreeBiome;
import com.eclipsekingdom.fractalforest.phylo.Species;
import com.eclipsekingdom.fractalforest.util.ConsoleSender;
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
                for (TreeSpawner spawner : spawners) {
                    String spawnerKey = biomeKey + "." + spawner.getSpecies().toString();
                    config.set(spawnerKey + ".chance", spawner.getChance());
                    config.set(spawnerKey + ".min", spawner.getMin());
                    config.set(spawnerKey + ".max", spawner.getMax());
                }
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
                                for (String speciesName : config.getConfigurationSection(biomeKey).getKeys(false)) {
                                    Species species = Species.from(speciesName);
                                    if (species != null) {
                                        String spawnerKey = biomeKey + "." + speciesName;
                                        double chance = config.getDouble(spawnerKey + ".chance", 0.2);
                                        int min = config.getInt(spawnerKey + ".min", 1);
                                        int max = config.getInt(spawnerKey + ".max", 2);
                                        spawners.add(new TreeSpawner(species, chance, min, max));
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
