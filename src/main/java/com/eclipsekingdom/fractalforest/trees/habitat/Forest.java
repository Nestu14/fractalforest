package com.eclipsekingdom.fractalforest.trees.habitat;

import com.google.common.collect.ImmutableSet;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Set;

public class Forest implements IHabitat {

    @Override
    public boolean canPlantAt(Location location) {
        Block above = location.clone().add(0, 1, 0).getBlock();
        return soilMaterials.contains(location.getBlock().getType()) && above.isPassable() && !liquid.contains(above.getType());
    }

    private Set<Material> soilMaterials = new ImmutableSet.Builder<Material>()
            .add(Material.GRASS_BLOCK)
            .add(Material.DIRT)
            .add(Material.PODZOL)
            .add(Material.COARSE_DIRT)
            .build();


    private Set<Material> liquid = new ImmutableSet.Builder<Material>()
            .add(Material.WATER)
            .add(Material.LAVA)
            .build();
}
