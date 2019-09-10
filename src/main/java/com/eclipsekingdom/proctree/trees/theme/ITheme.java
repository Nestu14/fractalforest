package com.eclipsekingdom.proctree.trees.theme;

import com.eclipsekingdom.proctree.trees.material.IMaterialFactory;

public interface ITheme {

    IMaterialFactory getLeaf();
    IMaterialFactory getThickBranch();
    IMaterialFactory getThinBranch();
    IMaterialFactory getRoot();

}
