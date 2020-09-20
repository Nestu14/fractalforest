package com.eclipsekingdom.fractalforest.util.theme.type;

import com.eclipsekingdom.fractalforest.sys.Version;
import com.eclipsekingdom.fractalforest.util.theme.ITheme;
import com.eclipsekingdom.fractalforest.util.X.FMaterial;
import com.eclipsekingdom.fractalforest.util.theme.material.IMaterialFactory;
import com.eclipsekingdom.fractalforest.util.theme.material.MaterialJumble;
import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import org.bukkit.Material;

import java.util.Collections;
import java.util.Set;

public class FloweringHawthornTheme implements ITheme {

    @Override
    public IMaterialFactory getLeaf() {
        if (Version.current.value >= 112) {
            return new MaterialJumble().add(FMaterial.PINK_TERRACOTTA).add(FMaterial.PINK_CONCRETE).add(FMaterial.PINK_WOOL);
        } else {
            return new MaterialJumble().add(FMaterial.PINK_TERRACOTTA).add(FMaterial.PINK_WOOL);
        }
    }

    @Override
    public IMaterialFactory getThickBranch() {
        return new MaterialSingleton(FMaterial.OAK_WOOD);
    }

    @Override
    public IMaterialFactory getThinBranch() {
        return new MaterialSingleton(FMaterial.SPRUCE_FENCE);
    }

    @Override
    public IMaterialFactory getRoot() {
        return new MaterialSingleton(FMaterial.OAK_WOOD);
    }

    @Override
    public Set<Material> getSelfMaterials() {
        return Collections.EMPTY_SET;
    }
}
