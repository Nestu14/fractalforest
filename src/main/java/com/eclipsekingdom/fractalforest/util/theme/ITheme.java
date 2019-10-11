package com.eclipsekingdom.fractalforest.util.theme;

import com.eclipsekingdom.fractalforest.util.theme.material.IMaterialFactory;
import org.bukkit.Material;

import java.util.Set;

public interface ITheme {

    IMaterialFactory getLeaf();
    IMaterialFactory getThickBranch();
    IMaterialFactory getThinBranch();
    IMaterialFactory getRoot();

    Set<Material> getSelfMaterials();

}
