package com.eclipsekingdom.fractalforest.gen;

import com.eclipsekingdom.fractalforest.gen.pop.TreePopulator;
import org.bukkit.World;

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
        WorldUtil.reset(world);
        if (enabled) world.getPopulators().add(treePopulator);
    }

    public TreePopulator getTreePopulator() {
        return treePopulator;
    }

    public boolean hasTreePopulator() {
        return treePopulator != null;
    }

    public void setTreePopulator(World world, TreePopulator treePopulator) {
        WorldUtil.reset(world);
        this.treePopulator = treePopulator;
        if (enabled && treePopulator != null) {
            world.getPopulators().add(treePopulator);
        } else {
            enabled = false;
        }
    }


}
