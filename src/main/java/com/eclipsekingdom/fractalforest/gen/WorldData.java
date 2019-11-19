package com.eclipsekingdom.fractalforest.gen;

import com.eclipsekingdom.fractalforest.gen.pop.TreePopulator;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.List;

public class WorldData {

    private TreePopulator treePopulator;
    private boolean enabled;

    public WorldData(World world, TreePopulator treePopulator, boolean enabled) {
        this.enabled = enabled;
        setTreePopulator(world, treePopulator);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void toggleEnabled(World world) {
        enabled = !enabled;
        resetWorld(world);
        if (enabled) world.getPopulators().add(treePopulator);
    }

    public TreePopulator getTreePopulator() {
        return treePopulator;
    }

    public boolean hasTreePopulator() {
        return treePopulator != null;
    }

    public void setTreePopulator(World world, TreePopulator treePopulator) {
        resetWorld(world);
        this.treePopulator = treePopulator;
        if (enabled && treePopulator != null) world.getPopulators().add(treePopulator);
    }

    public static void resetWorld(World world) {
        List<BlockPopulator> populators = world.getPopulators();
        for (int i = populators.size() - 1; i >= 0; i--) {
            BlockPopulator populator = populators.get(i);
            if (populator == null || populator.toString().contains("TreePopulator")) {
                populators.remove(i);
            }
        }
    }

}
