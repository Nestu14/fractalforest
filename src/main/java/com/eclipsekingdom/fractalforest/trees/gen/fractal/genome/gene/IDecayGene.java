package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene;

import com.eclipsekingdom.fractalforest.util.math.range.Bounds;

public interface IDecayGene {

    double next();
    double next(double angle, Bounds angleBounds);
    double next(double previous);

}
