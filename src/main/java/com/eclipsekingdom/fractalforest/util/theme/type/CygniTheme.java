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

public class CygniTheme implements ITheme {

    @Override
    public IMaterialFactory getLeaf() {
        if (Version.current.value >= 112) {
            return new MaterialJumble().add(FMaterial.PURPLE_CONCRETE).add(FMaterial.PURPLE_WOOL).add(FMaterial.PURPLE_STAINED_GLASS);
        } else {
            return new MaterialJumble().add(FMaterial.PURPLE_WOOL).add(FMaterial.PURPLE_STAINED_GLASS);
        }

    }

    @Override
    public IMaterialFactory getThickBranch() {
        return new MaterialSingleton(FMaterial.OBSIDIAN);
    }

    @Override
    public IMaterialFactory getThinBranch() {
        return new MaterialSingleton(FMaterial.OBSIDIAN);
    }

    @Override
    public IMaterialFactory getRoot() {
        return new MaterialSingleton(FMaterial.OBSIDIAN);
    }

    @Override
    public Set<Material> getSelfMaterials() {
        return Collections.EMPTY_SET;
    }
}