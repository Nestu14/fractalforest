package com.eclipsekingdom.fractalforest.trees.fractal.genome.gene;

import com.eclipsekingdom.fractalforest.util.range.Bounds;

public interface IAngleGene {
    double next();
    Bounds getBounds();
}
