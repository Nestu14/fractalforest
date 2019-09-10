package com.eclipsekingdom.proctree.trees.fractal.genome.gene;

import com.eclipsekingdom.proctree.util.TreeMath;
import com.eclipsekingdom.proctree.util.range.Bounds;

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

}
