package com.eclipsekingdom.proctree.trees.fractal.genome.gene;

import com.eclipsekingdom.proctree.trees.fractal.Branch;
import com.eclipsekingdom.proctree.trees.Root;

public interface IRootGene {
    Root next(Branch trunk);
    int nextAmount();
}
