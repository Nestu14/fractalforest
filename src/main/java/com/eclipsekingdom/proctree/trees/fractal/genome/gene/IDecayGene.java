package com.eclipsekingdom.proctree.trees.fractal.genome.gene;

import com.eclipsekingdom.proctree.util.range.Bounds;

public interface IDecayGene {

    double next();
    double next(double angle, Bounds angleBounds);

}
