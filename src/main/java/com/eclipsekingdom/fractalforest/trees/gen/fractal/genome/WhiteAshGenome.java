package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome;

import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene.*;
import com.eclipsekingdom.fractalforest.util.math.functions.Exponential;
import com.eclipsekingdom.fractalforest.util.math.range.Bounds;

public class WhiteAshGenome extends Genome {
    public WhiteAshGenome() {
        /*
        super(new ClumpGene(0.149), new SplitGene(3, 4), new AngleGene(new Bounds(0.39, 0.58)),
                new DecayGene(new Bounds(0.149, 0.541)), new TrunkGene(new Bounds(6.22, 7.75), new Bounds(1.03, 1.70), new Bounds(-0.21, 0.21)),
                new LeafGene(0.7, 0.72), new RootGene(3, 5, new Exponential(0.63, 1.5), new Bounds(1, 1), new Bounds(4, 5)));
        */
        super(new ClumpGene(0.149), new SplitGene(3, 4), new AngleGene(new Bounds(0.39, 0.58)),
                new DecayGene(new Bounds(0.149, 0.541)), new TrunkGene(new Bounds(6.22, 7.75), new Bounds(1.03, 1.66), new Bounds(-0.21, 0.21)),
                new LeafGene(0.7, 0.92), new RootGene(3, 5, new Exponential(0.63, 1.5), new Bounds(1, 1), new Bounds(4, 5)));
    }
}
