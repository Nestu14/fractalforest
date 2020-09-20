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

public class FallMapleTheme implements ITheme {

    @Override
    public IMaterialFactory getLeaf() {
        if (Version.current.value >= 112) {
            return new MaterialJumble().add(FMaterial.BROWN_TERRACOTTA)
                    .add(FMaterial.ORANGE_TERRACOTTA)
                    .add(FMaterial.ORANGE_CONCRETE)
                    .add(FMaterial.ORANGE_WOOL)
                    .add(FMaterial.YELLOW_TERRACOTTA)
                    .add(FMaterial.YELLOW_CONCRETE)
                    .add(FMaterial.YELLOW_WOOL)
                    .add(FMaterial.RED_TERRACOTTA)
                    .add(FMaterial.RED_CONCRETE)
                    .add(FMaterial.RED_WOOL);
        } else {
            return new MaterialJumble().add(FMaterial.BROWN_TERRACOTTA)
                    .add(FMaterial.ORANGE_TERRACOTTA)
                    .add(FMaterial.ORANGE_WOOL)
                    .add(FMaterial.YELLOW_TERRACOTTA)
                    .add(FMaterial.YELLOW_WOOL)
                    .add(FMaterial.RED_TERRACOTTA)
                    .add(FMaterial.RED_WOOL);
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
