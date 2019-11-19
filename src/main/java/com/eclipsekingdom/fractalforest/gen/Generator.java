package com.eclipsekingdom.fractalforest.gen;

import org.bukkit.World;

import java.util.HashMap;
import java.util.Map;

public class Generator {

    private static Map<World, WorldData> worldToData = new HashMap<>();
    private static GeneratorFlatFile generatorFlatFile = new GeneratorFlatFile();

    public Generator() {
        load();
    }

    private void load() {
        worldToData.putAll(generatorFlatFile.fetch());
    }

    public static void save() {
        generatorFlatFile.store(worldToData);
    }

    public static WorldData getWorldData(World world) {
        if (worldToData.containsKey(world)) {
            return worldToData.get(world);
        } else {
            WorldData worldData = new WorldData(world,null, false);
            worldToData.put(world, worldData);
            return worldData;
        }
    }

}
