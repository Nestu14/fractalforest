package com.eclipsekingdom.fractalforest.worldgen;

import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.List;

public class WorldUtil {

    public static void reset(World world) {
        List<BlockPopulator> populators = world.getPopulators();
        for (int i = populators.size() - 1; i >= 0; i--) {
            BlockPopulator populator = populators.get(i);
            if (populator == null || populator.toString().contains("TreePopulator")) {
                populators.remove(i);
            }
        }
    }

}
