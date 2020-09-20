package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene;

import com.eclipsekingdom.fractalforest.trees.gen.Branch;
import com.eclipsekingdom.fractalforest.trees.gen.Root;

public interface IRootGene {
    Root next(Branch trunk);
    int nextAmount();
}
