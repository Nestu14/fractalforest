package com.eclipsekingdom.fractalforest.trees.habitat;

import com.google.common.collect.ImmutableSet;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Set;

public class Nether implements IHabitat {

    @Override
    public boolean canPlantAt(Location location) {
        Block above = location.clone().add(0, 1, 0).getBlock();
        return soilMaterials.contains(location.getBlock().getType()) && above.isPassable() && above.getType() != Material.WATER;
    }

    private Set<Material> soilMaterials = new ImmutableSet.Builder<Material>()
            .add(Material.SOUL_SAND)
            .build();

}
