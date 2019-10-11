package com.eclipsekingdom.fractalforest.util.theme;

import com.eclipsekingdom.fractalforest.util.theme.material.IMaterialFactory;
import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;

public class Theme implements ITheme {

    protected IMaterialFactory leaf;
    protected IMaterialFactory thickBranch;
    protected IMaterialFactory thinBranch;
    protected IMaterialFactory root;

    @Override
    public IMaterialFactory getLeaf() {
        return leaf;
    }

    @Override
    public IMaterialFactory getThickBranch() {
        return thickBranch;
    }

    @Override
    public IMaterialFactory getThinBranch() {
        return thinBranch;
    }

    @Override
    public IMaterialFactory getRoot() {
        return root;
    }

    @Override
    public Set<Material> getSelfMaterials() {
        Set<Material> selfMaterial = new HashSet<>();
        selfMaterial.addAll(leaf.domain());
        selfMaterial.addAll(thickBranch.domain());
        selfMaterial.addAll(thinBranch.domain());
        selfMaterial.addAll(root.domain());
        return selfMaterial;
    }
}