package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.type;

import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.Genome;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene.*;
import com.eclipsekingdom.fractalforest.util.math.functions.Exponential;
import com.eclipsekingdom.fractalforest.util.math.range.Bounds;

public class ElmGenome extends Genome {
    public ElmGenome() {
        super(new ClumpGene(0.05),
                new SplitGene(3,4),
                new AngleGene(new Bounds(0, Math.PI/3)),
                new DecayGene(new Bounds(0.23,0.34)),
                new TrunkGene(new Bounds(9,12), new Bounds(1.5,2), new Bounds(-1,1)),
                new LeafGene(1, 2.2),
                new RootGene(4,6,new Exponential(1,1.2), new Bounds(1,1),new Bounds(4,5)));
    }
}