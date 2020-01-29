package com.eclipsekingdom.fractalforest.util.theme.type;

import com.eclipsekingdom.fractalforest.sys.Version;
import com.eclipsekingdom.fractalforest.util.X.FMaterial;
import com.eclipsekingdom.fractalforest.util.theme.ITheme;
import com.eclipsekingdom.fractalforest.util.theme.material.IMaterialFactory;
import com.eclipsekingdom.fractalforest.util.theme.material.MaterialJumble;
import com.eclipsekingdom.fractalforest.util.theme.material.MaterialSingleton;
import org.bukkit.Material;

import java.util.Collections;
import java.util.Set;

public class SecchiTheme implements ITheme {

    @Override
    public IMaterialFactory getLeaf() {
        if (Version.current.value >= 112) {
            return new MaterialJumble().add(FMaterial.MAGENTA_STAINED_GLASS).add(FMaterial.MAGENTA_CONCRETE).add(FMaterial.MAGENTA_WOOL);
        } else {
            return new MaterialJumble().add(FMaterial.MAGENTA_STAINED_GLASS).add(FMaterial.MAGENTA_WOOL);
        }

    }

    private static boolean legacy = Version.current.value <= 112;

    @Override
    public IMaterialFactory getThickBranch() {
        if (!legacy) {
            return new MaterialSingleton(FMaterial.MUSHROOM_STEM);
        } else {
            return new MaterialSingleton(FMaterial.MUSHROOM_LEGACY);
        }
    }

    @Override
    public IMaterialFactory getThinBranch() {
        if (!legacy) {
            return new MaterialSingleton(FMaterial.MUSHROOM_STEM);
        } else {
            return new MaterialSingleton(FMaterial.MUSHROOM_LEGACY);
        }
    }

    @Override
    public IMaterialFactory getRoot() {
        if (!legacy) {
            return new MaterialSingleton(FMaterial.MUSHROOM_STEM);
        } else {
            return new MaterialSingleton(FMaterial.MUSHROOM_LEGACY);
        }
    }

    @Override
    public Set<Material> getSelfMaterials() {
        return Collections.EMPTY_SET;
    }
}