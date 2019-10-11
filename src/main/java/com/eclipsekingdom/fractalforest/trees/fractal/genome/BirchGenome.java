package com.eclipsekingdom.fractalforest.trees.fractal.genome;

import com.eclipsekingdom.fractalforest.trees.fractal.genome.gene.*;
import com.eclipsekingdom.fractalforest.util.functions.Exponential;
import com.eclipsekingdom.fractalforest.util.range.Bounds;

public class BirchGenome extends Genome {
    public BirchGenome() {
        super(new ClumpGene(0.05),
                new SplitGene(3,4),
                new AngleGene(new Bounds(0, Math.PI/3.6d)),
                new DecayGene(new Bounds(0.27,0.40)),
                new TrunkGene(new Bounds(5,8), new Bounds(1,1.6), new Bounds(-1,1)),
                new LeafGene(1, 2.4),
                new RootGene(2,3,new Exponential(0.8,1), new Bounds(1,1),new Bounds(3,4)));
    }
}
