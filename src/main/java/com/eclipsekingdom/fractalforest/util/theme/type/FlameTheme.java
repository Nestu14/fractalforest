package com.eclipsekingdom.fractalforest.util.theme.type;

import com.eclipsekingdom.fractalforest.sys.Version;
import com.eclipsekingdom.fractalforest.util.theme.ITheme;
import com.eclipsekingdom.fractalforest.util.X.FMaterial;
import com.eclipsekingdom.fractalforest.util.theme.material.IMaterialFactory;
import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import com.eclipsekingdom.fractalforest.util.theme.material.WeightedMaterialJumble;
import org.bukkit.Material;

import java.util.Collections;
import java.util.Set;

public class FlameTheme implements ITheme {

    @Override
    public IMaterialFactory getLeaf() {
        if (Version.current.value >= 110) {
            return new MaterialSingleton(FMaterial.MAGMA_BLOCK);
        } else {
            return new MaterialSingleton(FMaterial.GLOWSTONE);
        }
    }

    @Override
    public IMaterialFactory getThickBranch() {
        if (Version.current.value >= 110) {
            return new WeightedMaterialJumble().add(FMaterial.RED_NETHER_BRICKS, 3).add(FMaterial.NETHERRACK, 1);
        } else {
            return new WeightedMaterialJumble().add(FMaterial.NETHER_BRICKS, 3).add(FMaterial.NETHERRACK, 1);
        }
    }

    @Override
    public IMaterialFactory getThinBranch() {
        if (Version.current.value >= 114) {
            return new MaterialSingleton(FMaterial.RED_NETHER_BRICK_WALL);
        } else {
            return new MaterialSingleton(FMaterial.NETHER_BRICK_FENCE);
        }

    }

    @Override
    public IMaterialFactory getRoot() {
        if (Version.current.value >= 110) {
            return new WeightedMaterialJumble().add(FMaterial.RED_NETHER_BRICKS, 3).add(FMaterial.NETHERRACK, 1);
        } else {
            return new WeightedMaterialJumble().add(FMaterial.NETHER_BRICKS, 3).add(FMaterial.NETHERRACK, 1);
        }
    }

    @Override
    public Set<Material> getSelfMaterials() {
        return Collections.EMPTY_SET;
    }
}
