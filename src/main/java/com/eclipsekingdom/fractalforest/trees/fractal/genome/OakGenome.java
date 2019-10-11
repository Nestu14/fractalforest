package com.eclipsekingdom.fractalforest.trees.fractal.genome;

import com.eclipsekingdom.fractalforest.util.functions.Exponential;
import com.eclipsekingdom.fractalforest.trees.fractal.genome.gene.*;
import com.eclipsekingdom.fractalforest.util.range.Bounds;

public class OakGenome extends Genome {
    public OakGenome() {
        super(new ClumpGene(0.05),
                new SplitGene(3,4),
                new AngleGene(new Bounds(0, Math.PI/3)),
                new DecayGene(new Bounds(0.23,0.34)),
                new TrunkGene(new Bounds(6,9), new Bounds(1.5,2), new Bounds(-1,1)),
                new LeafGene(1, 2.2),
                new RootGene(4,6,new Exponential(1,1.2), new Bounds(1,1),new Bounds(4,5)));
    }
}
