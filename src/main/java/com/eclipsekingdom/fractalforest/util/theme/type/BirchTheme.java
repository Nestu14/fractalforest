package com.eclipsekingdom.fractalforest.util.theme.type;

import com.eclipsekingdom.fractalforest.util.theme.ITheme;
import com.eclipsekingdom.fractalforest.util.theme.material.IMaterialFactory;
import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import org.bukkit.Material;

import java.util.Collections;
import java.util.Set;

public class BirchTheme implements ITheme {

    @Override
    public IMaterialFactory getLeaf() {
        return new MaterialSingleton(Material.BIRCH_LEAVES);
    }

    @Override
    public IMaterialFactory getThickBranch() {
        return new MaterialSingleton(Material.BIRCH_WOOD);
    }

    @Override
    public IMaterialFactory getThinBranch() {
        return new MaterialSingleton(Material.BIRCH_FENCE);
    }

    @Override
    public IMaterialFactory getRoot() {
        return new MaterialSingleton(Material.BIRCH_WOOD);
    }

    @Override
    public Set<Material> getSelfMaterials() {
        return Collections.EMPTY_SET;
    }
}
