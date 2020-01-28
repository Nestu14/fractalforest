package com.eclipsekingdom.fractalforest.trees.habitat;

import com.eclipsekingdom.fractalforest.util.TreeUtil;
import com.eclipsekingdom.fractalforest.util.X.XMaterial;
import com.google.common.collect.ImmutableSet;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Set;

public class End implements IHabitat {

    @Override
    public boolean canPlantAt(Location location) {
        Block above = location.clone().add(0, 1, 0).getBlock();
        return soilMaterials.contains(location.getBlock().getType()) && TreeUtil.isPassable(above.getType()) && !liquid.contains(above.getType());
    }

    private Set<Material> soilMaterials = new ImmutableSet.Builder<Material>()
            .add(XMaterial.END_STONE.parseMaterial())
            .build();


    private Set<Material> liquid = new ImmutableSet.Builder<Material>()
            .add(Material.WATER)
            .add(Material.LAVA)
            .build();
}