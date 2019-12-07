package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene;

import com.eclipsekingdom.fractalforest.util.math.TreeMath;
import com.eclipsekingdom.fractalforest.util.math.range.Bounds;

public class DecayGene implements IDecayGene {

    private  Bounds bounds;

    public DecayGene(Bounds bounds){
        this.bounds = bounds;
    }

    @Override
    public double next() {
        return bounds.nextValue();
    }

    @Override
    public double next(double angle, Bounds angleBounds) {
        return TreeMath.map(Math.abs(angle), angleBounds, bounds);
    }

    @Override
    public double next(double previous) {
        return bounds.nextValue();
    }

}
