package com.eclipsekingdom.fractalforest.util.theme.type;

import com.eclipsekingdom.fractalforest.sys.Version;
import com.eclipsekingdom.fractalforest.util.theme.ITheme;
import com.eclipsekingdom.fractalforest.util.X.FMaterial;
import com.eclipsekingdom.fractalforest.util.theme.material.IMaterialFactory;
import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import org.bukkit.Material;

import java.util.Collections;
import java.util.Set;

public class BirchTheme implements ITheme {

    @Override
    public IMaterialFactory getLeaf() {
        return new MaterialSingleton(FMaterial.BIRCH_LEAVES);
    }

    @Override
    public IMaterialFactory getThickBranch() {
        return new MaterialSingleton(FMaterial.BIRCH_WOOD);
    }

    @Override
    public IMaterialFactory getThinBranch() {
        if (Version.current.value >= 114) {
            return new MaterialSingleton(FMaterial.DIORITE_WALL);
        } else {
            return new MaterialSingleton(FMaterial.BIRCH_FENCE);
        }
    }

    @Override
    public IMaterialFactory getRoot() {
        return new MaterialSingleton(FMaterial.BIRCH_WOOD);
    }

    @Override
    public Set<Material> getSelfMaterials() {
        return Collections.EMPTY_SET;
    }
}
