package com.eclipsekingdom.fractalforest.trees.habitat;

import com.eclipsekingdom.fractalforest.util.TreeUtil;
import com.eclipsekingdom.fractalforest.util.X.XMaterial;
import com.google.common.collect.ImmutableSet;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Set;

public class Swamp implements IHabitat {

    @Override
    public boolean canPlantAt(Location location) {
        Block above = location.clone().add(0, 1, 0).getBlock();
        return soilMaterials.contains(location.getBlock().getType()) && TreeUtil.isPassable(above.getType()) && !liquid.contains(above.getType());
    }

    @Override
    public boolean isSoil(Material material) {
        return false;
    }

    private Set<Material> soilMaterials = new ImmutableSet.Builder<Material>()
            .add(XMaterial.GRASS_BLOCK.parseMaterial())
            .add(XMaterial.DIRT.parseMaterial())
            .add(XMaterial.PODZOL.parseMaterial())
            .add(XMaterial.COARSE_DIRT.parseMaterial())
            .build();


    private Set<Material> liquid = new ImmutableSet.Builder<Material>()
            .add(Material.WATER)
            .add(Material.LAVA)
            .build();
}