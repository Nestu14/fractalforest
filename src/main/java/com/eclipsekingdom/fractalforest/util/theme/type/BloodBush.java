package com.eclipsekingdom.fractalforest.util.theme.type;

import com.eclipsekingdom.fractalforest.util.theme.ITheme;
import com.eclipsekingdom.fractalforest.util.theme.material.IMaterialFactory;
import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import org.bukkit.Material;

import java.util.Collections;
import java.util.Set;

public class BloodBush implements ITheme {

    @Override
    public IMaterialFactory getLeaf() {
        return new MaterialSingleton(Material.NETHER_WART_BLOCK);
    }

    @Override
    public IMaterialFactory getThickBranch() {
        return new MaterialSingleton(Material.NETHER_BRICKS);
    }

    @Override
    public IMaterialFactory getThinBranch() {
        return new MaterialSingleton(Material.NETHER_BRICK_FENCE);
    }

    @Override
    public IMaterialFactory getRoot() {
        return new MaterialSingleton(Material.NETHER_BRICKS);
    }

    @Override
    public Set<Material> getSelfMaterials() {
        return Collections.EMPTY_SET;
    }
}
