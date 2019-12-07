package com.eclipsekingdom.fractalforest.util.theme.type;

import com.eclipsekingdom.fractalforest.util.theme.ITheme;
import com.eclipsekingdom.fractalforest.util.theme.material.IMaterialFactory;
import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import com.eclipsekingdom.fractalforest.util.theme.material.WeightedMaterialJumble;
import org.bukkit.Material;

import java.util.Collections;
import java.util.Set;

public class FlameTheme implements ITheme {

    @Override
    public IMaterialFactory getLeaf() {
        return new MaterialSingleton(Material.MAGMA_BLOCK);
    }

    @Override
    public IMaterialFactory getThickBranch() {
        return new WeightedMaterialJumble().add(Material.RED_NETHER_BRICKS, 3).add(Material.NETHERRACK, 1);
    }

    @Override
    public IMaterialFactory getThinBranch() {
        return new MaterialSingleton(Material.RED_NETHER_BRICK_WALL);
    }

    @Override
    public IMaterialFactory getRoot() {
        return new WeightedMaterialJumble().add(Material.RED_NETHER_BRICKS, 3).add(Material.NETHERRACK, 1);
    }

    @Override
    public Set<Material> getSelfMaterials() {
        return Collections.EMPTY_SET;
    }
}
