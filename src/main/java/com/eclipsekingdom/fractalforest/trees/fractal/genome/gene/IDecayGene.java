package com.eclipsekingdom.fractalforest.trees.fractal.genome.gene;

import com.eclipsekingdom.fractalforest.util.range.Bounds;

public interface IDecayGene {

    double next();
    double next(double angle, Bounds angleBounds);
    double next(double previous);

}
