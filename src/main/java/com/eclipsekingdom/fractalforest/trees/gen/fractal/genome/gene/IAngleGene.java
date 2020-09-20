package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene;

import com.eclipsekingdom.fractalforest.util.math.range.Bounds;

public interface IAngleGene {
    double next();
    Bounds getBounds();
}
