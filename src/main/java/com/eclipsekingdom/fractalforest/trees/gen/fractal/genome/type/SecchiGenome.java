package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.type;

import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.Genome;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene.*;
import com.eclipsekingdom.fractalforest.util.math.functions.Exponential;
import com.eclipsekingdom.fractalforest.util.math.range.Bounds;

public class SecchiGenome extends Genome {
    public SecchiGenome() {
        super(new ClumpGene(0.00),
                new SplitGene(1,1),
                new AngleGene(new Bounds(0.43, 2.17)),
                new DecayGene(new Bounds(0.149,0.258)),
                new TrunkGene(new Bounds(4.14,9.74), new Bounds(0.34,0.57), new Bounds(-2,2)),
                new LeafGene(0.44, 4.4),
                new RootGene(1,5,new Exponential(0.7,1.11), new Bounds(0.3,0.54),new Bounds(1.88,3.78)));
    }
}