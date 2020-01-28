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

    @Override
    public IMaterialFactory getThickBranch() {
        return new MaterialSingleton(FMaterial.MUSHROOM);
    }

    @Override
    public IMaterialFactory getThinBranch() {
        return new MaterialSingleton(FMaterial.MUSHROOM);
    }

    @Override
    public IMaterialFactory getRoot() {
        return new MaterialSingleton(FMaterial.MUSHROOM);
    }

    @Override
    public Set<Material> getSelfMaterials() {
        return Collections.EMPTY_SET;
    }
}