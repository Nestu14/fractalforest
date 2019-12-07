package com.eclipsekingdom.fractalforest.worldgen;

import com.eclipsekingdom.fractalforest.worldgen.pop.PopCache;
import com.eclipsekingdom.fractalforest.worldgen.pop.TreePopulator;
import com.eclipsekingdom.fractalforest.util.system.ConsoleSender;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.eclipsekingdom.fractalforest.util.language.Message.CONSOLE_FILE_ERROR;

public class GeneratorFlatFile {

    private static final File file = new File("plugins/FractalForest/Data", "generator.yml");
    private static final FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    private static final String header = "Generator";

    public void store(Map<World, WorldData> worldToData) {
        config.set(header, null);
        for (Map.Entry<World, WorldData> entry : worldToData.entrySet()) {
            World world = entry.getKey();
            WorldData worldData = entry.getValue();
            if (worldData.hasTreePopulator()) {
                String key = header + "." + world.getName();
                config.set(key + ".enabled", worldData.isEnabled());
                config.set(key + ".populator", worldData.getTreePopulator().getName());
            }
        }
        save();
    }

    public Map<World, WorldData> fetch() {
        Map<World, WorldData> worldToData = new HashMap<>();
        if (config.contains(header)) {
            for (String worldName : config.getConfigurationSection(header).getKeys(false)) {
                try {
                    World world = Bukkit.getWorld(worldName);
                    if (world != null) {
                        String key = header + "." + worldName;
                        boolean enabled = config.getBoolean(key + ".enabled", false);
                        TreePopulator treePopulator = PopCache.getPopulator(config.getString(key + ".populator", ""));
                        if (treePopulator != null) {
                            worldToData.put(world, new WorldData(world, treePopulator, enabled));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return worldToData;
    }


    private void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            ConsoleSender.sendMessage(CONSOLE_FILE_ERROR.getFromFile(file.getName()));
        }
    }

}
