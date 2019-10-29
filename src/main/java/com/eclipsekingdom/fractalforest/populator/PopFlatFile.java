package com.eclipsekingdom.fractalforest.populator;

import com.eclipsekingdom.fractalforest.phylo.Species;
import com.eclipsekingdom.fractalforest.util.ConsoleSender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.World;

import java.io.File;
import java.util.*;

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
            List<String> worldNames = new ArrayList<>();
            for (World world : pop.getWorlds()) {
                worldNames.add(world.getName());
            }
            config.set(key + ".worlds", worldNames);
            config.set(key + ".enabled", pop.isEnabled());
            for (Map.Entry<Biome, List<TreeSpawner>> entry : pop.getBiomeToTreeSpawner().entrySet()) {
                Biome biome = entry.getKey();
                List<TreeSpawner> spawners = entry.getValue();
                String biomeKey = key + ".generator." + biome.toString();
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
                try {
                    boolean valid = true;
                    String key = header + "." + name;
                    List<String> worldNames = config.getStringList(key + ".worlds");
                    List<World> worlds = new ArrayList<>();
                    for (String worldName : worldNames) {
                        World w = Bukkit.getWorld(worldName);
                        if (worldName != null) {
                            worlds.add(w);
                        }
                    }
                    if (worlds.size() < 1) {
                        valid = false;
                    }

                    boolean enabled = config.getBoolean(key + ".enabled", true);

                    LinkedHashMap<Biome, List<TreeSpawner>> biomeToTreeSpawners = new LinkedHashMap<>();

                    if (config.contains(key + ".generator")) {
                        for (String biomeString : config.getConfigurationSection(key + ".generator").getKeys(false)) {
                            try {
                                Biome biome = Biome.valueOf(biomeString);
                                String biomeKey = key + ".generator." + biomeString;
                                List<TreeSpawner> spawners = new ArrayList<>();
                                if (config.contains(biomeKey)) {
                                    for (String speciesName : config.getConfigurationSection(biomeKey).getKeys(false)) {
                                        Species species = Species.from(speciesName);
                                        if(species != null){
                                            String spawnerKey = biomeKey + "." + speciesName;
                                            double chance = config.getDouble(spawnerKey + ".chance", 0.2);
                                            int min = config.getInt(spawnerKey + ".min", 1);
                                            int max = config.getInt(spawnerKey + ".max", 2);
                                            spawners.add(new TreeSpawner(species, chance, min, max));
                                        }
                                    }
                                }
                                if(spawners.size() > 0){
                                    biomeToTreeSpawners.put(biome, spawners);
                                }
                            } catch (Exception e) {
                            }
                        }
                    }

                    if (biomeToTreeSpawners.keySet().size() < 1) {
                        valid = false;
                    }

                    if (valid) {
                        TreePopulator populator = new TreePopulator(name, worlds, enabled, biomeToTreeSpawners);
                        populator.initialize();
                        tPops.add(populator);
                    }
                } catch (Exception e) {
                    ConsoleSender.sendMessage(CONSOLE_TPOP_ERROR.getColoredFromPop(name, ChatColor.RESET));
                }
            }
        }
        return tPops;
    }


    private void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            ConsoleSender.sendMessage(ChatColor.RED + CONSOLE_FILE_ERROR.getFromFile(file.getName()));
        }
    }

}
