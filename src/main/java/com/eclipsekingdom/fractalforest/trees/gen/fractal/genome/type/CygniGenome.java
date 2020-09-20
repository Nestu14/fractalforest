package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.type;

import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.Genome;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene.*;
import com.eclipsekingdom.fractalforest.util.math.functions.Exponential;
import com.eclipsekingdom.fractalforest.util.math.range.Bounds;

public class CygniGenome extends Genome {
    public CygniGenome() {
        super(new ClumpGene(0.00),
                new SplitGene(2,3),
                new AngleGene(new Bounds(0.31, 1.3)),
                new DecayGene(new Bounds(0.098,0.409)),
                new TrunkGene(new Bounds(2.96,5.39), new Bounds(0.52,1.15), new Bounds(-0.10,0.10)),
                new LeafGene(0.41, 1.39),
                new RootGene(4,8,new Exponential(1,0.51), new Bounds(0.31,0.56),new Bounds(4.27,7.34)));
    }
}