package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.type;

import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.Genome;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene.*;
import com.eclipsekingdom.fractalforest.util.math.functions.Exponential;
import com.eclipsekingdom.fractalforest.util.math.range.Bounds;

public class HickoryGenome extends Genome {
    public HickoryGenome() {
        super(new ClumpGene(0.149), new SplitGene(3, 4), new AngleGene(new Bounds(0.39, 0.58)),
                new DecayGene(new Bounds(0.152, 0.533)), new TrunkGene(new Bounds(4.44, 5.55), new Bounds(0.66, 1.6), new Bounds(-0.11, 0.11)),
                new LeafGene(0.5, 2.7), new RootGene(2, 4, new Exponential(0.43, 1.3), new Bounds(1, 1), new Bounds(4, 5)));
    }
}
