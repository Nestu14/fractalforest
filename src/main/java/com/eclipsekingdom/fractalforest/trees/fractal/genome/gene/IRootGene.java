package com.eclipsekingdom.fractalforest.trees.fractal.genome.gene;

import com.eclipsekingdom.fractalforest.trees.Branch;
import com.eclipsekingdom.fractalforest.trees.Root;

public interface IRootGene {
    Root next(Branch trunk);
    int nextAmount();
}
