package com.eclipsekingdom.fractalforest.util.theme.type;

import com.eclipsekingdom.fractalforest.util.theme.ITheme;
import com.eclipsekingdom.fractalforest.util.theme.material.IMaterialFactory;
import com.eclipsekingdom.fractalforest.util.theme.material.MaterialJumble;
import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import org.bukkit.Material;

import java.util.Collections;
import java.util.Set;

public class FallOakTheme implements ITheme {

    @Override
    public IMaterialFactory getLeaf() {
        return new MaterialJumble()
                .add(Material.BROWN_TERRACOTTA)
                .add(Material.BROWN_CONCRETE)
                .add(Material.BROWN_WOOL)
                .add(Material.RED_TERRACOTTA)
                .add(Material.RED_CONCRETE)
                .add(Material.RED_WOOL);
    }

    @Override
    public IMaterialFactory getThickBranch() {
        return new MaterialSingleton(Material.OAK_WOOD);
    }

    @Override
    public IMaterialFactory getThinBranch() {
        return new MaterialSingleton(Material.SPRUCE_FENCE);
    }

    @Override
    public IMaterialFactory getRoot() {
        return new MaterialSingleton(Material.OAK_WOOD);
    }

    @Override
    public Set<Material> getSelfMaterials() {
        return Collections.EMPTY_SET;
    }
}
