package com.eclipsekingdom.fractalforest.util.theme;

import com.eclipsekingdom.fractalforest.util.X.FMaterial;
import com.eclipsekingdom.fractalforest.util.theme.material.IMaterialFactory;
import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;

public class Theme implements ITheme {

    private final IMaterialFactory leaf;
    private final IMaterialFactory thickBranch;
    private final IMaterialFactory thinBranch;
    private final IMaterialFactory root;
    private Set<Material> selfMaterials;

    public Theme(ITheme theme) {
        this.leaf = theme.getLeaf();
        this.thickBranch = theme.getThickBranch();
        this.thinBranch = theme.getThinBranch();
        this.root = theme.getRoot();
        selfMaterials = calcSelfMaterials();
    }

    private HashSet<Material> calcSelfMaterials() {
        HashSet<Material> self = new HashSet<>();
        for (FMaterial fMaterial : leaf.domain()) {
            self.add(fMaterial.getMaterial());
        }

        for (FMaterial fMaterial : thickBranch.domain()) {
            self.add(fMaterial.getMaterial());
        }

        for (FMaterial fMaterial : thinBranch.domain()) {
            self.add(fMaterial.getMaterial());
        }

        for (FMaterial fMaterial : root.domain()) {
            self.add(fMaterial.getMaterial());
        }
        return self;
    }

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
        return selfMaterials;
    }
}