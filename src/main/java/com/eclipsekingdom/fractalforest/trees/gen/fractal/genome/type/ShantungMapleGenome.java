package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.type;

import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.Genome;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene.*;
import com.eclipsekingdom.fractalforest.util.math.functions.Exponential;
import com.eclipsekingdom.fractalforest.util.math.range.Bounds;

public class ShantungMapleGenome extends Genome {
    public ShantungMapleGenome() {
        super(new ClumpGene(0.05),
                new SplitGene(3,4),
                new AngleGene(new Bounds(0, Math.PI/3)),
                new DecayGene(new Bounds(0.23,0.353)),
                new TrunkGene(new Bounds(4.08,5.95), new Bounds(1.3,1.79), new Bounds(-0.06,0.06)),
                new LeafGene(1, 2.2),
                new RootGene(2,4,new Exponential(1,1.2), new Bounds(0.46,1),new Bounds(2.4,3.52)));
    }
}