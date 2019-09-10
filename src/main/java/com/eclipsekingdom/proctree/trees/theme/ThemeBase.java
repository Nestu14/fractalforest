package com.eclipsekingdom.proctree.trees.theme;

import com.eclipsekingdom.proctree.trees.material.IMaterialFactory;

public class ThemeBase implements ITheme {

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
}